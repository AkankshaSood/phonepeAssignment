package com.example.phonepeassignment.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.phonepeassignment.R
import com.example.phonepeassignment.ui.adapters.VenueListAdapter
import com.example.phonepeassignment.utils.ResponseWrapper
import com.example.phonepeassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VenueListFragment : Fragment(R.layout.fragment_venue_list) {

    private var venueRv: RecyclerView? = null
    private var venueListAdapter: VenueListAdapter? = null
    private var seekBar: SeekBar? = null
    private val viewModel: MainViewModel by activityViewModels()
    private val handler = Handler()
    private val debounceDelay = 300L
    private var range: Int = 12

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        venueRv = view.findViewById(R.id.list_rv)
        seekBar = view.findViewById<SeekBar>(R.id.range_seekbar)
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                handler.removeCallbacksAndMessages(null)
                range = progress
                handler.postDelayed(debounceRunnable, debounceDelay)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when user starts interacting with the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when user stops interacting with the SeekBar
            }
        })
        initRecyclerView()
        fetchDataAndInitObservers()
    }

    private val debounceRunnable = Runnable {
        // Handle the debounced progress value (e.g., update UI or perform an action)
        // For demonstration purposes, a Toast message is shown here
        viewModel.range = range
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition > 0
            val isTotalMoreThanVisible = totalItemCount >= 10

            val shouldPaginate = !isLoading && !isLastPage && isAtLastItem  && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.fetchVenues()
                isScrolling = false
            }
        }
    }

    private fun fetchDataAndInitObservers() {
        viewModel.venueResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResponseWrapper.Success -> {
                    isLoading = false
                    //hideProgressBar()
                    Log.d("heyyy", "response occured!")
                    response.data?.let {
                        Log.d("heyyy", "response occured! 2")
                        venueListAdapter?.let { it2 ->
                            Log.d("heyyy", "response occured! 3")
                            it2.differ.submitList(it.venues)
                        }
                    }
                }
                is ResponseWrapper.Error -> {
                    isLoading = false
                    Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
//                    hideProgressBar()
                }
                else -> {
                    isLoading = true
//                    showProgressBar()

                }
            }
        }
    }

    private fun initRecyclerView() {
        venueListAdapter = VenueListAdapter()
        venueRv?.apply {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = venueListAdapter
            addOnScrollListener(this@VenueListFragment.scrollListener)
        }
    }

}
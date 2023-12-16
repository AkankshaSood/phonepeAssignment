package com.example.phonepeassignment.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.phonepeassignment.databinding.VenueItemLayoutBinding
import com.example.phonepeassignment.models.VenuesItem

class VenueListAdapter() : RecyclerView.Adapter<VenueListAdapter.NewsItemViewHolder>() {

    private var differCallback = object : DiffUtil.ItemCallback<VenuesItem>() {
        override fun areContentsTheSame(oldItem: VenuesItem, newItem: VenuesItem) = oldItem == newItem
        override fun areItemsTheSame(oldItem: VenuesItem, newItem: VenuesItem): Boolean = oldItem.id == newItem.id
    }
    val differ = AsyncListDiffer<VenuesItem>(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            VenueItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.heading.text= item.name
        holder.subheading.text = item.displayLocation
        Glide.with(holder.itemView).load(item.url).into(holder.locationIv)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsItemViewHolder(binding: VenueItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val locationIv: ImageView = binding.locationIv
        val heading: TextView = binding.headingTv
        val subheading: TextView = binding.subheadingTv
    }

}
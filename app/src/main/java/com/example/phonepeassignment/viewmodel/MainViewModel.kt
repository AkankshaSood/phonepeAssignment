package com.example.phonepeassignment.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.phonepeassignment.models.VenueResponse
import com.example.phonepeassignment.repository.Repository
import com.example.phonepeassignment.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: Repository): ViewModel() {
    var venueResponsePage = 1
    var venueResponseLocal: VenueResponse? = null
    var range: Int = 12
        set(value) {
            field = value
            refetchVenues()
        }

    private fun refetchVenues() {
        venueResponsePage = 1;
        venueResponseLocal = null;
        fetchVenues()
    }

    var userLocation: Location? = null
        set(value) {
            field = value;
            refetchVenues()
        }
    var venueResponse: MutableLiveData<ResponseWrapper<VenueResponse>> = MutableLiveData()

    private fun handleVenuesResponse(response: Response<VenueResponse>): ResponseWrapper<VenueResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                venueResponsePage++;
                if (venueResponseLocal == null) {
                    venueResponseLocal = it
                } else {
                    it.venues?.let {list ->
                        venueResponseLocal?.venues?.addAll(list)
                    }
                }
                return ResponseWrapper.Success(venueResponseLocal ?: it)
            }
        }
        return ResponseWrapper.Error(message = response.message())
    }

    fun fetchVenues() {
        Log.d("heyyyy", "getching venues ${range}")
        viewModelScope.launch(Dispatchers.IO) {
            userLocation?.let {
                venueResponse.postValue(
                    handleVenuesResponse(
                        repository.getVenues(venueResponsePage, it.latitude.toString(), it.longitude.toString(), "${range}mi" )))
            }
            //venueResponse.postValue(ResponseWrapper.Loading)

        }
    }

    fun test() {
        Log.d("heyy", "test")
    }
}
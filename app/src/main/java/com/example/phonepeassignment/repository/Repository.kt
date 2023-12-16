package com.example.phonepeassignment.repository

import com.example.phonepeassignment.api.Api
import com.example.phonepeassignment.models.VenueResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    suspend fun getVenues(pageNumber: Int, lat: String, lon: String, range: String = "12mi"): Response<VenueResponse> {
       return api.getVenues(lat = "12.971599", lon = "77.594566", range = range, pageNumber = pageNumber)
    }
}
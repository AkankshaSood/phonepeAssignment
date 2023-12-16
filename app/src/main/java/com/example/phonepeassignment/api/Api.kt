package com.example.phonepeassignment.api

import com.example.phonepeassignment.models.VenueResponse
import com.example.phonepeassignment.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("2/venues")
    suspend fun getVenues(
        @Query("lon") lon: String,
        @Query("lat") lat: String,
        @Query("range") range: String,
        @Query("q") countryCode: String? = null,
        @Query("per_page") responseSize: Int = 10,
        @Query("page") pageNumber: Int = 1,
        @Query("client_id") apiKey: String = Constants.API_KEY
    ): Response<VenueResponse>
}


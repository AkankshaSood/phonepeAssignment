package com.example.phonepeassignment.repository

import com.example.phonepeassignment.api.Api
import com.example.phonepeassignment.db.VenuesDb
import com.example.phonepeassignment.models.VenueResponse
import com.example.phonepeassignment.models.VenuesItem
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val db: VenuesDb) {

    suspend fun getVenues(pageNumber: Int, lat: String, lon: String, range: String): Response<VenueResponse>? {
       return api.getVenues(lat = "12.971599", lon = "77.594566", range = range, pageNumber = pageNumber)
    }

    suspend fun insertAll(venues: MutableList<VenuesItem>) {
        db.getVenuesDao().clearTable()
        db.getVenuesDao().insertUsers(venues)
    }

    fun getAllVenues() = db.getVenuesDao().getVenues()
}
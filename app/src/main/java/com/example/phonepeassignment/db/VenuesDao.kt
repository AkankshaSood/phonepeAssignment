package com.example.phonepeassignment.db
import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phonepeassignment.models.VenuesItem

@Dao
interface VenuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(venuesItem: List<VenuesItem>)

    // live data does not work with suspend objects
    @Query("SELECT * FROM venues_table")
    fun getVenues(): LiveData<List<VenuesItem>>

    @Delete
    suspend fun deleteAll(venuesItem: List<VenuesItem>)
    @Query("DELETE FROM venues_table")
    suspend fun clearTable()
}
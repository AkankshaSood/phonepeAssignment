package com.example.phonepeassignment.db

import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.phonepeassignment.models.VenuesItem

@Database(entities = [VenuesItem::class], version = 1)
abstract class VenuesDb: RoomDatabase() {
    abstract fun getVenuesDao(): VenuesDao
}
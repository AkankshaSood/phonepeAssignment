package com.example.phonepeassignment.di

import android.content.Context
import androidx.room.Room
import com.example.phonepeassignment.db.VenuesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DBModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(applicationContext, VenuesDb::class.java, "venue_db").build()
}
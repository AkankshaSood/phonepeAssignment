package com.example.wayfairassignment.di

import android.content.Context
import androidx.room.Room
//import com.example.newsapp.db.NewsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
//class DBModule {
//    @Provides
//    @Singleton
//    fun provideDB(@ApplicationContext applicationContext: Context) =
//        Room.databaseBuilder(applicationContext, NewsDb::class.java, "article_db").build()
//}
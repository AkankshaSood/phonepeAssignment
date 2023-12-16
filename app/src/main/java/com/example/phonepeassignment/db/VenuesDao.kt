package com.example.newsapp.db

import androidx.room.Dao

@Dao
interface VenuesDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun upsert(playlistArticleMapping: PlaylistArticleMapping): Long
////
////    // live data does not work with suspend objects
////    @Query("SELECT * FROM article_table")
////    fun getArticles(): LiveData<List<ArticlesItem>>
////
//    @Delete
//    suspend fun deleteArticles(playlistArticleMapping: PlaylistArticleMapping)
//
//    // live data does not work with suspend objects
//    @Query("SELECT playlistTitle FROM playlist_article_mapping_table where articleUrl = :articleUrl")
//    fun getPlaylistsOfArticle(articleUrl: String): LiveData<List<String>>
}
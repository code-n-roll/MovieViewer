package com.karanchuk.core.dao.movies

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("""
        SELECt m.*
        FROM Movies AS m
        INNER JOIN MovieFeedType AS f
        ON m.id = f.movieId
        WHERE f.feedType = :feedType
        ORDER BY f.position ASC
    """)
    fun pagingSource(feedType: com.karanchuk.common.model.domain.FeedType): PagingSource<Int, com.karanchuk.core.entity.movies.DbMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<com.karanchuk.core.entity.movies.DbMovie>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedCrossRefs(refs: List<com.karanchuk.core.entity.movies.DbMovieFeedCrossRef>)

    @Query("DELETE FROM MovieFeedType WHERE feedType = :feedType")
    suspend fun clearFeedCrossRefs(feedType: com.karanchuk.common.model.domain.FeedType)

    @Query("SELECT COUNT(movieId) FROM MovieFeedType WHERE feedType = :feedType")
    suspend fun getMovieCountForFeed(feedType: com.karanchuk.common.model.domain.FeedType): Int
}
package com.karanchuk.movieviewer.repository.movies.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieFeedCrossRef
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import kotlinx.coroutines.flow.Flow

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
    fun pagingSource(feedType: FeedType): PagingSource<Int, DbMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<DbMovie>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedCrossRefs(refs: List<DbMovieFeedCrossRef>)

    @Query("DELETE FROM MovieFeedType WHERE feedType = :feedType")
    suspend fun clearFeedCrossRefs(feedType: FeedType)

    @Query("SELECT COUNT(movieId) FROM MovieFeedType WHERE feedType = :feedType")
    suspend fun getMovieCountForFeed(feedType: FeedType): Int
}
package com.karanchuk.movieviewer.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karanchuk.movieviewer.data.source.local.model.DbMovie
import com.karanchuk.movieviewer.data.source.local.model.DbMovieFeedCrossRef
import com.karanchuk.movieviewer.data.source.local.model.FeedType
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query(
        """
       SELECT * FROM Movies
       WHERE id IN (
            SELECT movieId FROM MovieFeedType WHERE feedType = :feedType
       )
       ORDER BY (
            SELECT position FROM MovieFeedType WHERE movieId = Movies.id AND feedType = :feedType
       )
    """
    )
    fun observeAll(feedType: FeedType): Flow<List<DbMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<DbMovie>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedCrossRefs(refs: List<DbMovieFeedCrossRef>)
}
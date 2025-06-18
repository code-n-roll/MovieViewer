package com.karanchuk.movieviewer.repository.movies.db.dao

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
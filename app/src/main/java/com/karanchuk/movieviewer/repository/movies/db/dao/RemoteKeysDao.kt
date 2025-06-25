package com.karanchuk.movieviewer.repository.movies.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieRemoteKey
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM MovieRemoteKeys WHERE movieId = :movieId AND feedType = :feedType")
    suspend fun remoteKeysByMovieIdAndFeedType(movieId: Int, feedType: FeedType): DbMovieRemoteKey?

    @Query("DELETE FROM MovieRemoteKeys WHERE feedType = :feedType")
    suspend fun clearRemoteKeysByFeedType(feedType: FeedType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<DbMovieRemoteKey>)
}
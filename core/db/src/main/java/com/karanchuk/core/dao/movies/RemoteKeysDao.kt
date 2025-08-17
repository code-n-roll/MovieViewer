package com.karanchuk.core.dao.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM MovieRemoteKeys WHERE movieId = :movieId AND feedType = :feedType")
    suspend fun remoteKeysByMovieIdAndFeedType(movieId: Int, feedType: com.karanchuk.common.model.domain.FeedType): com.karanchuk.core.entity.movies.DbMovieRemoteKey?

    @Query("DELETE FROM MovieRemoteKeys WHERE feedType = :feedType")
    suspend fun clearRemoteKeysByFeedType(feedType: com.karanchuk.common.model.domain.FeedType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<com.karanchuk.core.entity.movies.DbMovieRemoteKey>)
}
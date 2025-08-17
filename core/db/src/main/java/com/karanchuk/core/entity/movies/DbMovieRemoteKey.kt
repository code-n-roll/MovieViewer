package com.karanchuk.core.entity.movies

import androidx.room.Entity

@Entity(
    tableName = "MovieRemoteKeys",
    primaryKeys = ["movieId", "feedType"]
)
data class DbMovieRemoteKey(
    val movieId: Int,
    val feedType: com.karanchuk.common.model.domain.FeedType,
    val prevKey: Int?,
    val nextKey: Int?,
)
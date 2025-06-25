package com.karanchuk.movieviewer.repository.movies.db.model

import androidx.room.Entity

@Entity(
    tableName = "MovieRemoteKeys",
    primaryKeys = ["movieId", "feedType"]
)
data class DbMovieRemoteKey(
    val movieId: Int,
    val feedType: FeedType,
    val prevKey: Int?,
    val nextKey: Int?,
)
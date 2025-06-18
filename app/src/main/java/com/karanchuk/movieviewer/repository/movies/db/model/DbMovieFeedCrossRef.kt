package com.karanchuk.movieviewer.repository.movies.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "MovieFeedType",
    primaryKeys = ["feedType", "movieId"],
    foreignKeys = [
        ForeignKey(
            entity = DbMovie::class,
            parentColumns = ["id"],
            childColumns = ["movieId"]
        )
    ],
    indices = [Index("movieId")]
)
data class DbMovieFeedCrossRef(
    val feedType: FeedType,
    val movieId: Int,
    val position: Int,
)
package com.karanchuk.core.entity.movies

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.karanchuk.common.model.domain.FeedType

@Entity(
    tableName = "MovieFeedType",
    primaryKeys = ["feedType", "movieId"],
    foreignKeys = [
        ForeignKey(
            entity = DbMovie::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("movieId"),
        Index(value = ["feedType", "position"])
    ]
)
data class DbMovieFeedCrossRef(
    val feedType: FeedType,
    val movieId: Int,
    val position: Int,
)
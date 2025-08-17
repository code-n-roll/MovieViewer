package com.karanchuk.core.entity.favorite_movies

import androidx.room.Entity
import androidx.room.TypeConverters
import com.karanchuk.core.converter.FavoriteMovieConverter
import java.time.Instant

@Entity(
    tableName = "FavoriteMovies",
    primaryKeys = ["movieId"]
)
@TypeConverters(FavoriteMovieConverter::class)
data class DbFavoriteMovie(
    val movieId: Int,
    val addedAt: Instant = Instant.now(),
)

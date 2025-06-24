package com.karanchuk.movieviewer.repository.favorite_movies.db.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.repository.favorite_movies.db.converters.FavoriteMovieConverter
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

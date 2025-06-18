package com.karanchuk.movieviewer.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.data.source.local.converters.MoviesConverter
import java.time.LocalDate

@Entity(
    tableName = "Movies",
)
@TypeConverters(MoviesConverter::class)
data class DbMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val voteAverage: Double,
    val releaseDate: LocalDate,
)
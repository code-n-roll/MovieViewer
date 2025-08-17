package com.karanchuk.core.entity.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.karanchuk.core.converter.MoviesConverter
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
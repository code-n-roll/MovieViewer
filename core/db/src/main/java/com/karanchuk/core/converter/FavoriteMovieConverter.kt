package com.karanchuk.core.converter

import androidx.room.TypeConverter
import java.time.Instant

object FavoriteMovieConverter {

    @TypeConverter
    @JvmStatic
    fun toInstant(value: String?): Instant? {
        return Instant.parse(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromInstant(value: Instant?): String? {
        return value?.toString()
    }
}
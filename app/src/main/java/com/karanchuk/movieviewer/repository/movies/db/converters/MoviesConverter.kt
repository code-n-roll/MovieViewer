package com.karanchuk.movieviewer.repository.movies.db.converters

import androidx.room.TypeConverter
import com.karanchuk.movieviewer.util.fromServerDateFormatString
import com.karanchuk.movieviewer.util.toServerDateFormatString
import java.time.LocalDate

object MoviesConverter {

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? {
        return value?.fromServerDateFormatString()
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.toServerDateFormatString()
    }
}
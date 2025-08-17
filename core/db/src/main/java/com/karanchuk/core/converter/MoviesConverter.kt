package com.karanchuk.core.converter

import androidx.room.TypeConverter
import com.karanchuk.common.util.fromServerDateFormatString
import com.karanchuk.common.util.toServerDateFormatString
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
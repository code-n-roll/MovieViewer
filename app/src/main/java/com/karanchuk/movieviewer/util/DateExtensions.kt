package com.karanchuk.movieviewer.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val SERVER_DATE_FORMAT = "yyyy-MM-dd"

fun LocalDate.toServerDateFormatString(): String {
    val formatter = DateTimeFormatter.ofPattern(SERVER_DATE_FORMAT)
    return formatter.format(this)
}

fun String.fromServerDateFormatString(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(SERVER_DATE_FORMAT)
    return try {
        LocalDate.parse(this, formatter)
    } catch (e: Exception) {
        LocalDate.MIN
    }
}
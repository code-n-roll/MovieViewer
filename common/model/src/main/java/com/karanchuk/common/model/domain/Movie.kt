package com.karanchuk.common.model.domain

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val voteAverage: Double,
    val releaseDate: LocalDate,
)
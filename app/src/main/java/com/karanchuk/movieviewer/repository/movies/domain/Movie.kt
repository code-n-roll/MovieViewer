package com.karanchuk.movieviewer.repository.movies.domain

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val voteAverage: Double,
    val releaseDate: LocalDate,
) {
    companion object {
        val Preview = Movie(
            id = 0,
            title = "The Shawshank Redemption",
            posterUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1",
            voteAverage = 9.2,
            releaseDate = LocalDate.of(1994, 9, 23),
        )
    }
}
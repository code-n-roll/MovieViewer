package com.karanchuk.movieviewer.movies.ui.components.section

import com.karanchuk.movieviewer.movies.ui.components.card.MovieCardState

data class MovieSectionState(
    val title: String,
    val movies: List<MovieCardState>,
) {
    companion object {
        val Preview = MovieSectionState(
            title = "Movies",
            movies = listOf(
                MovieCardState.Preview,
                MovieCardState.Preview,
                MovieCardState.Preview,
            )
        )
    }
}
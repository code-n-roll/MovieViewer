package com.karanchuk.movieviewer.movies.tea.core

import com.karanchuk.movieviewer.data.source.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
)
package com.karanchuk.movieviewer.feature.movies.tea.core

sealed interface MoviesEffect {

    data object ShowToast : MoviesEffect
    data class OpenMovieDetails(val movieId: Int) : MoviesEffect
}
package com.karanchuk.movieviewer.movies.tea.core

sealed interface MoviesEffect {

    data object ShowToast : MoviesEffect
    data class OpenMovieDetails(val movieId: Int) : MoviesEffect
}
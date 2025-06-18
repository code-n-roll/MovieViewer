package com.karanchuk.movieviewer.movies.tea.core

import com.karanchuk.movieviewer.data.source.Movie

sealed interface MoviesEvent {

    sealed interface MoviesLoading : MoviesEvent {
        data object Started : MoviesLoading
        class Succeed(val movies: List<Movie>) : MoviesLoading
        class Failed(val error: Throwable) : MoviesLoading
    }
}

sealed interface MoviesUiEvent : MoviesEvent {

    data object Init : MoviesUiEvent

    data class OpenMovieDetails(val movieId: Int) : MoviesUiEvent

    data class FavoriteClick(val movieId: Int) : MoviesUiEvent
}
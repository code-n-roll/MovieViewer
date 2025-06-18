package com.karanchuk.movieviewer.movies.tea.core

import com.karanchuk.movieviewer.data.source.Movie

sealed interface MoviesEvent {
    data class MoviesLoaded(val movies: List<Movie>) : MoviesEvent
}

sealed interface MoviesUiEvent : MoviesEvent {

    data object Init : MoviesUiEvent

    data class OpenMovieDetails(val movieId: Int) : MoviesUiEvent

    data class FavoriteClick(val movieId: Int) : MoviesUiEvent
}
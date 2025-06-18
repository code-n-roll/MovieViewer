package com.karanchuk.movieviewer.movies.ui

import com.karanchuk.movieviewer.movies.ui.components.MovieCardState

sealed interface MoviesUiState {

    data object Loading : MoviesUiState
    data object Error : MoviesUiState
    data class Content(val movies: List<MovieCardState>) : MoviesUiState {
        companion object {
            val Preview = Content(
                movies = listOf(
                    MovieCardState.Preview,
                    MovieCardState.Preview,
                    MovieCardState.Preview,
                )
            )
        }
    }
}
package com.karanchuk.movieviewer.feature.movies.tea.core

import com.karanchuk.movieviewer.repository.movies.domain.Movie
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType

sealed interface MoviesEvent {

    sealed interface MoviesLoading : MoviesEvent {
        data object Started : MoviesLoading
        class Succeed(val result: Map<FeedType, List<Movie>>) : MoviesLoading
        class Failed(val error: Throwable) : MoviesLoading
    }
}

sealed interface MoviesUiEvent : MoviesEvent {

    data object Init : MoviesUiEvent

    data class OpenMovieDetails(val movieId: Int) : MoviesUiEvent

    data class FavoriteClick(val movieId: Int) : MoviesUiEvent
}
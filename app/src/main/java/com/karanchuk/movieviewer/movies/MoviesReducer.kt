package com.karanchuk.movieviewer.movies

import com.karanchuk.movieviewer.core.tea.dsl.DslReducer
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand.LoadMovies
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand.ObserveMovies
import com.karanchuk.movieviewer.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent.MoviesLoading
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.movies.tea.core.MoviesUiEvent
import com.karanchuk.movieviewer.util.Lce
import com.karanchuk.movieviewer.util.toLoadingContentAware

class MoviesReducer : DslReducer<MoviesCommand, MoviesEffect, MoviesEvent, MoviesState>() {

    override fun reduce(event: MoviesEvent) = when (event) {
        is MoviesUiEvent -> reduceUiEvent(event)
        is MoviesLoading -> reduceMoviesLoading(event)
        else -> Unit
    }

    private fun reduceMoviesLoading(event: MoviesLoading) = when (event) {
        is MoviesLoading.Started -> state { copy(movies = movies.toLoadingContentAware()) }
        is MoviesLoading.Succeed -> state { copy(movies = Lce.Content(event.movies)) }
        is MoviesLoading.Failed -> state { copy(movies = Lce.Error(event.error)) }
    }

    private fun reduceUiEvent(uiEvent: MoviesUiEvent) = when (uiEvent) {
        is MoviesUiEvent.Init -> { commands(ObserveMovies, LoadMovies) }
        is MoviesUiEvent.OpenMovieDetails -> { effects(MoviesEffect.OpenMovieDetails(movieId = uiEvent.movieId)) }
        is MoviesUiEvent.FavoriteClick -> { effects(MoviesEffect.ShowToast) }
    }
}
package com.karanchuk.movieviewer.movies

import com.karanchuk.movieviewer.core.tea.dsl.DslReducer
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.movies.tea.core.MoviesUiEvent

class MoviesReducer : DslReducer<MoviesCommand, MoviesEffect, MoviesEvent, MoviesState>() {

    override fun reduce(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.MoviesLoaded -> {
                state {
                    copy(movies = event.movies)
                }
            }
            is MoviesUiEvent -> reduceUiEvent(event)
            else -> Unit
        }
    }

    private fun reduceUiEvent(uiEvent: MoviesUiEvent) {
        when (uiEvent) {
            is MoviesUiEvent.Init -> {
                commands(
                    MoviesCommand.ObserveMovies,
                    MoviesCommand.LoadMovies,
                )
            }
            is MoviesUiEvent.OpenMovieDetails -> { effects(MoviesEffect.OpenMovieDetails(movieId = uiEvent.movieId)) }
            is MoviesUiEvent.FavoriteClick -> { effects(MoviesEffect.ShowToast) }
        }
    }
}
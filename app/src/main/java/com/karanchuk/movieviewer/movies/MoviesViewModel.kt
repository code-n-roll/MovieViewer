package com.karanchuk.movieviewer.movies

import com.karanchuk.movieviewer.core.tea.TeaViewModel
import com.karanchuk.movieviewer.data.source.MoviesRepository
import com.karanchuk.movieviewer.movies.tea.actor.LoadMoviesActor
import com.karanchuk.movieviewer.movies.tea.actor.ObserveMoviesActor
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.movies.tea.core.MoviesUiEvent
import com.karanchuk.movieviewer.movies.ui.MoviesUiState
import com.karanchuk.movieviewer.movies.ui.MoviesUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : TeaViewModel<MoviesCommand, MoviesEffect, MoviesEvent, MoviesUiEvent, MoviesState, MoviesUiState>(
    initialState = MoviesState(),
    actors = setOf(
        ObserveMoviesActor(moviesRepository),
        LoadMoviesActor(moviesRepository),
    ),
    reducer = MoviesReducer(),
    uiStateMapper = MoviesUiStateMapper(),
    initialEvents = listOf(MoviesUiEvent.Init),
)
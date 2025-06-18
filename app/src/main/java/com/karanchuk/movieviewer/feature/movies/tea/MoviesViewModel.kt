package com.karanchuk.movieviewer.feature.movies.tea

import com.karanchuk.movieviewer.core.tea.TeaViewModel
import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
import com.karanchuk.movieviewer.feature.movies.tea.actor.LoadMoviesActor
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesEvent
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesUiEvent
import com.karanchuk.movieviewer.feature.movies.ui.MoviesUiState
import com.karanchuk.movieviewer.feature.movies.ui.MoviesUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : TeaViewModel<MoviesCommand, MoviesEffect, MoviesEvent, MoviesUiEvent, MoviesState, MoviesUiState>(
    initialState = MoviesState(),
    actors = setOf(
        LoadMoviesActor(moviesRepository),
    ),
    reducer = MoviesReducer(),
    uiStateMapper = MoviesUiStateMapper(),
    initialEvents = listOf(MoviesUiEvent.Init),
)
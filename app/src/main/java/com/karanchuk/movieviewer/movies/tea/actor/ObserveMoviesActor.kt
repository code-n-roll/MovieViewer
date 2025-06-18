package com.karanchuk.movieviewer.movies.tea.actor

import com.karanchuk.movieviewer.core.tea.component.Actor
import com.karanchuk.movieviewer.data.source.MoviesRepository
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand.ObserveMovies
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent.MoviesLoading
import com.karanchuk.movieviewer.util.startWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class ObserveMoviesActor(
    private val moviesRepository: MoviesRepository,
) : Actor<MoviesCommand, MoviesEvent> {

    override fun act(commands: Flow<MoviesCommand>): Flow<MoviesEvent> {
        return commands.filterIsInstance<ObserveMovies>()
            .flatMapLatest(::handleCommand)
    }

    private fun handleCommand(command: ObserveMovies): Flow<MoviesLoading> {
        return moviesRepository.getMoviesFlow()
            .map(MoviesLoading::Succeed)
            .startWith(MoviesLoading.Started)
    }
}
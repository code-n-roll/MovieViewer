package com.karanchuk.movieviewer.movies.tea.actor

import com.karanchuk.movieviewer.core.tea.component.Actor
import com.karanchuk.movieviewer.data.source.MoviesRepository
import com.karanchuk.movieviewer.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.movies.tea.core.MoviesEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class ObserveMoviesActor(
    private val moviesRepository: MoviesRepository,
) : Actor<MoviesCommand, MoviesEvent> {

    override fun act(commands: Flow<MoviesCommand>): Flow<MoviesEvent> {
        return commands.filterIsInstance<MoviesCommand.ObserveMovies>()
            .flatMapLatest(::handleCommand)
    }

    private fun handleCommand(command: MoviesCommand.ObserveMovies): Flow<MoviesEvent> {
        return moviesRepository.getMoviesFlow()
            .map {
                MoviesEvent.MoviesLoaded(
                    movies = it
                )
            }
    }
}
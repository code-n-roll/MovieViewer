package com.karanchuk.movieviewer.feature.movies.tea.actor

import com.karanchuk.movieviewer.core.tea.component.Actor
import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesCommand
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class LoadMoviesActor(
    private val moviesRepository: MoviesRepository,
) : Actor<MoviesCommand, MoviesEvent> {

    override fun act(commands: Flow<MoviesCommand>): Flow<MoviesEvent> {
        return commands.filterIsInstance<MoviesCommand.LoadMovies>()
            .flatMapLatest(::handleCommand)
    }

    private fun handleCommand(command: MoviesCommand.LoadMovies): Flow<MoviesEvent> = flow {
        emit(MoviesEvent.MoviesLoading.Started)

        val result = runCatching {
            coroutineScope {
                FeedType.entries.associateWith { feedType ->
                    async {
                        moviesRepository.loadMovies(feedType)
                        moviesRepository.getMoviesFlow(feedType).first()
                    }
                }.mapValues { (_, deferred) -> deferred.await() }
            }
        }

        result
            .onSuccess { emit(MoviesEvent.MoviesLoading.Succeed(it)) }
            .onFailure { emit(MoviesEvent.MoviesLoading.Failed(it)) }
    }
}
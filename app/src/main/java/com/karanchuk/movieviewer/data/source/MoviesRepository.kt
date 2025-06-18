package com.karanchuk.movieviewer.data.source

import com.karanchuk.movieviewer.data.source.local.dao.MoviesDao
import com.karanchuk.movieviewer.data.source.network.MovieApi
import com.karanchuk.movieviewer.di.DefaultDispatcher
import com.karanchuk.movieviewer.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesRepository {

    fun getMoviesFlow(): Flow<List<Movie>>

    suspend fun loadMovies()
}

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val networkDataSource: MovieApi,
    private val localDataSource: MoviesDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun loadMovies() {
        withContext(ioDispatcher) {
            runCatching { networkDataSource.getPopularMovies().results }
                .mapCatching { apiMovies -> apiMovies.apiToDbMovieList() }
                .onSuccess { localDataSource.insertAll(it) }
        }
    }

    override fun getMoviesFlow(): Flow<List<Movie>> {
        return localDataSource.observeAll().map { dbMovies ->
            withContext(defaultDispatcher) {
                dbMovies.toMovieList()
            }
        }
    }
}
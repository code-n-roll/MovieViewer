package com.karanchuk.movieviewer.repository.movies.domain

import com.karanchuk.movieviewer.common.ImagePrefetcher
import com.karanchuk.movieviewer.di.DefaultDispatcher
import com.karanchuk.movieviewer.di.IoDispatcher
import com.karanchuk.movieviewer.repository.movies.MoviesRepositoryImpl
import com.karanchuk.movieviewer.repository.movies.api.MovieApi
import com.karanchuk.movieviewer.repository.movies.db.dao.MoviesDao
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMoviesFlow(feedType: FeedType): Flow<List<Movie>>

    suspend fun loadMovies(feedType: FeedType)

    companion object {
        fun create(
            movieApi: MovieApi,
            moviesDao: MoviesDao,
            @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
            @IoDispatcher ioDispatcher: CoroutineDispatcher,
            imagePrefetcher: ImagePrefetcher,
        ): MoviesRepository = MoviesRepositoryImpl(
            networkDataSource = movieApi,
            localDataSource = moviesDao,
            defaultDispatcher = defaultDispatcher,
            ioDispatcher = ioDispatcher,
            imagePrefetcher = imagePrefetcher,
        )
    }
}
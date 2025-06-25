package com.karanchuk.movieviewer.di

import com.karanchuk.movieviewer.data.source.local.MovieViewerDatabase
import com.karanchuk.movieviewer.repository.favorite_movies.db.dao.FavoriteMovieDao
import com.karanchuk.movieviewer.repository.favorite_movies.domain.FavoriteMoviesRepository
import com.karanchuk.movieviewer.repository.movies.api.MovieApi
import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesMoviesRepository(
        movieApi: MovieApi,
        movieViewerDatabase: MovieViewerDatabase,
    ): MoviesRepository {
        return MoviesRepository.create(
            movieApi = movieApi,
            appDatabase = movieViewerDatabase,
        )
    }

    @Provides
    fun providesFavoriteMoviesRepository(
        dao: FavoriteMovieDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): FavoriteMoviesRepository {
        return FavoriteMoviesRepository.create(
            dao = dao,
            ioDispatcher = ioDispatcher
        )
    }
}
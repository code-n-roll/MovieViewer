package com.karanchuk.movieviewer.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.karanchuk.common.model.di.IoDispatcher
import com.karanchuk.core.dao.favorite_movie.FavoriteMovieDao
import com.karanchuk.movieviewer.repository.settings.domain.SettingsRepository
import com.karanchuk.repository.favorite_movies.FavoriteMoviesRepository
import com.karanchuk.repository.movies.domain.MoviesRepository
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
        movieApi: com.karanchuk.repository.movies.api.MovieApi,
        movieViewerDatabase: com.karanchuk.core.db.MovieViewerDatabase,
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
    
    @Provides
    fun providesSettingsRepository(
        dataStore: DataStore<Preferences>
    ): SettingsRepository {
        return SettingsRepository.create(dataStore)
    }
}
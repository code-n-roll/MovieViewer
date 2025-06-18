package com.karanchuk.movieviewer.di

import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
import com.karanchuk.movieviewer.repository.movies.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(moviesRepository: MoviesRepositoryImpl): MoviesRepository
}
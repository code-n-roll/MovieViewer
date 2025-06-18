package com.karanchuk.movieviewer.di

import com.karanchuk.movieviewer.data.source.MoviesRepository
import com.karanchuk.movieviewer.data.source.MoviesRepositoryImpl
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
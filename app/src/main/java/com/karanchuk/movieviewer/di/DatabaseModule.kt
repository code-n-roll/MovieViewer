package com.karanchuk.movieviewer.di

import android.content.Context
import androidx.room.Room
import com.karanchuk.core.dao.movies.MoviesDao
import com.karanchuk.core.db.MovieViewerDatabase
import com.karanchuk.movieviewer.BuildConfig
import com.karanchuk.core.dao.favorite_movie.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesMovieViewerDatabase(@ApplicationContext context: Context): MovieViewerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieViewerDatabase::class.java,
            "MovieViewer.db"
        ).apply {
            if (BuildConfig.DEBUG) {
                fallbackToDestructiveMigration()
                fallbackToDestructiveMigrationOnDowngrade()
            }
        }.build()
    }

    @Singleton
    @Provides
    fun providesMoviesDao(database: MovieViewerDatabase): MoviesDao = database.moviesDao()

    @Singleton
    @Provides
    fun providesFavoriteMoviesDao(database: MovieViewerDatabase): FavoriteMovieDao = database.favoriteMovieDao()
}
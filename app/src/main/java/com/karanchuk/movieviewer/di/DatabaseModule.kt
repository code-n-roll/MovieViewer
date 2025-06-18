package com.karanchuk.movieviewer.di

import android.content.Context
import androidx.room.Room
import com.karanchuk.movieviewer.BuildConfig
import com.karanchuk.movieviewer.data.source.local.MovieViewerDatabase
import com.karanchuk.movieviewer.data.source.local.dao.MoviesDao
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
    fun providesDatabase(@ApplicationContext context: Context): MovieViewerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieViewerDatabase::class.java,
            "MovieViewer.db"
        ).apply {
            if (BuildConfig.DEBUG) {
                fallbackToDestructiveMigration()
            }
        }.build()
    }

    @Provides
    fun providesMoviesDao(database: MovieViewerDatabase): MoviesDao = database.moviesDao()
}
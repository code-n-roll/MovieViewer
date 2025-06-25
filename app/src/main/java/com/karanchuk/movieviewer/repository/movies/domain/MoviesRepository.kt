package com.karanchuk.movieviewer.repository.movies.domain

import androidx.paging.PagingData
import com.karanchuk.movieviewer.data.source.local.MovieViewerDatabase
import com.karanchuk.movieviewer.repository.movies.MoviesRepositoryImpl
import com.karanchuk.movieviewer.repository.movies.api.MovieApi
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPagedMoviesFlow(feedType: FeedType): Flow<PagingData<Movie>>

    companion object {
        fun create(
            movieApi: MovieApi,
            appDatabase: MovieViewerDatabase,
        ): MoviesRepository = MoviesRepositoryImpl(
            api = movieApi,
            db = appDatabase,
        )
    }
}
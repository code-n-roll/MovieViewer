package com.karanchuk.repository.movies.domain

import androidx.paging.PagingData
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.core.db.MovieViewerDatabase
import com.karanchuk.repository.movies.MoviesRepositoryImpl
import com.karanchuk.repository.movies.api.MovieApi
import com.karanchuk.common.model.domain.FeedType
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
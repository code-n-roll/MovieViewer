package com.karanchuk.movieviewer.repository.movies.domain

import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMoviesFlow(feedType: FeedType): Flow<List<Movie>>

    suspend fun loadMovies(feedType: FeedType)
}
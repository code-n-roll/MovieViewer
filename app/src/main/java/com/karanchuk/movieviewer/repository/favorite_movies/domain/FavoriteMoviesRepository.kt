package com.karanchuk.movieviewer.repository.favorite_movies.domain

import com.karanchuk.movieviewer.di.IoDispatcher
import com.karanchuk.movieviewer.repository.favorite_movies.FavoriteMoviesRepositoryImpl
import com.karanchuk.movieviewer.repository.favorite_movies.db.dao.FavoriteMovieDao
import com.karanchuk.movieviewer.repository.movies.domain.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {

    suspend fun toggleFavorite(movieId: Int)

    fun isFavoriteFlow(movieId: Int): Flow<Boolean>

    fun getFavoriteMovies(sortedBy: DomainSort): Flow<List<Movie>>

    companion object {
        fun create(
            dao: FavoriteMovieDao,
            @IoDispatcher ioDispatcher: CoroutineDispatcher
        ): FavoriteMoviesRepository = FavoriteMoviesRepositoryImpl(
            dao = dao,
            ioDispatcher = ioDispatcher
        )
    }
}
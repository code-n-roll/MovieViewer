package com.karanchuk.repository.favorite_movies

import com.karanchuk.common.model.di.IoDispatcher
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.core.dao.favorite_movie.FavoriteMovieDao
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
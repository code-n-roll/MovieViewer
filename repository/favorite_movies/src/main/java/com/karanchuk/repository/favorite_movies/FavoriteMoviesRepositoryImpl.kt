package com.karanchuk.repository.favorite_movies

import androidx.sqlite.db.SimpleSQLiteQuery
import com.karanchuk.common.model.di.IoDispatcher
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.core.dao.favorite_movie.FavoriteMovieDao
import com.karanchuk.repository.movies.toMovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FavoriteMoviesRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FavoriteMoviesRepository {

    override suspend fun toggleFavorite(movieId: Int) {
        withContext(ioDispatcher) {
            dao.toggleFavorite(movieId)
        }
    }

    override fun isFavoriteFlow(movieId: Int): Flow<Boolean> {
        return dao.isFavoriteFlow(movieId = movieId)
    }

    override fun getFavoriteMovies(sortedBy: DomainSort): Flow<List<Movie>> {
        val sql = """
            SELECT m.*
            FROM movies AS m
            INNER JOIN FavoriteMovies AS f ON m.id = f.movieId
            ORDER BY ${sortedBy.toOrderBy()}
        """.trimIndent()
        val query = SimpleSQLiteQuery(sql)
        return dao.getFavoriteMovies(query).map { it.toMovieList() }
    }
}

private fun DomainSort.toOrderBy(): String = when(this) {
    DomainSort.RELEASE_DATE_ASC -> "releaseDate ASC"
    DomainSort.RELEASE_DATE_DESC -> "releaseDate DESC"
    DomainSort.TITLE_DESC -> "title DESC"
    DomainSort.TITLE_ASC -> "title ASC"
    DomainSort.DATE_ADDED_ASC -> "addedAt ASC"
    DomainSort.DATE_ADDED_DESC -> "addedAt DESC"
}

package com.karanchuk.movieviewer.repository.favorite_movies.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.karanchuk.movieviewer.repository.favorite_movies.db.model.DbFavoriteMovie
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Transaction
    suspend fun toggleFavorite(movieId: Int) {
        val isFavorite = isFavorite(movieId)
        if (isFavorite) {
            removeFavorite(movieId)
        } else {
            addFavorite(DbFavoriteMovie(movieId = movieId))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteMovie: DbFavoriteMovie)

    @Query("DELETE FROM FavoriteMovies WHERE movieId = :movieId")
    suspend fun removeFavorite(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteMovies WHERE movieId = :movieId)")
    fun isFavoriteFlow(movieId: Int): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM FavoriteMovies WHERE movieId = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean

    @RawQuery(
        observedEntities = [
            DbMovie::class,
            DbFavoriteMovie::class
        ]
    )
    fun getFavoriteMovies(query: SupportSQLiteQuery): Flow<List<DbMovie>>
}
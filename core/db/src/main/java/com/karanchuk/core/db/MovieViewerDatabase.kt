package com.karanchuk.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karanchuk.core.converter.MoviesConverter
import com.karanchuk.core.dao.favorite_movie.FavoriteMovieDao
import com.karanchuk.core.dao.movies.MoviesDao
import com.karanchuk.core.dao.movies.RemoteKeysDao
import com.karanchuk.core.entity.favorite_movies.DbFavoriteMovie
import com.karanchuk.core.entity.movies.DbMovie
import com.karanchuk.core.entity.movies.DbMovieFeedCrossRef
import com.karanchuk.core.entity.movies.DbMovieRemoteKey

@Database(
    entities = [
        DbMovie::class,
        DbMovieFeedCrossRef::class,
        DbFavoriteMovie::class,
        DbMovieRemoteKey::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(MoviesConverter::class)
abstract class MovieViewerDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
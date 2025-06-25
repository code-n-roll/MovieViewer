package com.karanchuk.movieviewer.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.repository.favorite_movies.db.dao.FavoriteMovieDao
import com.karanchuk.movieviewer.repository.favorite_movies.db.model.DbFavoriteMovie
import com.karanchuk.movieviewer.repository.movies.db.converters.MoviesConverter
import com.karanchuk.movieviewer.repository.movies.db.dao.MoviesDao
import com.karanchuk.movieviewer.repository.movies.db.dao.RemoteKeysDao
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieFeedCrossRef
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieRemoteKey

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
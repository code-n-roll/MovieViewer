package com.karanchuk.movieviewer.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.repository.movies.db.converters.MoviesConverter
import com.karanchuk.movieviewer.repository.movies.db.dao.MoviesDao
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieFeedCrossRef

@Database(
    entities = [
        DbMovie::class,
        DbMovieFeedCrossRef::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(MoviesConverter::class)
abstract class MovieViewerDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
package com.karanchuk.movieviewer.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.data.source.local.converters.MoviesConverter
import com.karanchuk.movieviewer.data.source.local.dao.MoviesDao
import com.karanchuk.movieviewer.data.source.local.model.DbMovie
import com.karanchuk.movieviewer.data.source.local.model.DbMovieFeedCrossRef

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
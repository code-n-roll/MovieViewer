package com.karanchuk.movieviewer.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karanchuk.movieviewer.data.source.local.dao.MoviesDao
import com.karanchuk.movieviewer.data.source.local.model.DbMovie

@Database(entities = [DbMovie::class], version = 1, exportSchema = false)
@TypeConverters(MoviesConverter::class)
abstract class MovieViewerDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
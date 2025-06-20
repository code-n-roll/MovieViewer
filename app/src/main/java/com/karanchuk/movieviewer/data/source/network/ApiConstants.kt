package com.karanchuk.movieviewer.data.source.network

import com.karanchuk.movieviewer.BuildConfig

object ApiConstants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185"
}
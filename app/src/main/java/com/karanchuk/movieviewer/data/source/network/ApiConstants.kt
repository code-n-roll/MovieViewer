package com.karanchuk.movieviewer.data.source.network

import com.karanchuk.movieviewer.BuildConfig

object ApiConstants {
    val API_KEY = BuildConfig.API_KEY
    val BASE_URL = "https://api.themoviedb.org/3/"
    val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185"
}
package com.karanchuk.movieviewer.data.source.network

import com.karanchuk.movieviewer.data.source.network.model.ApiMovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): ApiMovieResponse
}
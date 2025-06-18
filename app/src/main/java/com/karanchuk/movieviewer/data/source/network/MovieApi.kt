package com.karanchuk.movieviewer.data.source.network

import com.karanchuk.movieviewer.data.source.network.model.ApiMovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): ApiMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ApiMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): ApiMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): ApiMovieResponse
}
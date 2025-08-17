package com.karanchuk.repository.movies.api

import com.karanchuk.common.model.api.ApiMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): ApiMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): ApiMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): ApiMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): ApiMovieResponse
}
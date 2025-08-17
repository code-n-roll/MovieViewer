package com.karanchuk.common.model.api

import com.google.gson.annotations.SerializedName

data class ApiMovie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
)

data class ApiMovieResponse(
    val results: List<ApiMovie>,
    @SerializedName("total_pages") val totalPages: Int,
)
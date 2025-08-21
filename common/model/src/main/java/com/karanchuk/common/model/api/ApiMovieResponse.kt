package com.karanchuk.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovie(
    val id: Int,
    val title: String,
    @SerialName("poster_path") val posterUrl: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val voteAverage: Double,
)

@Serializable
data class ApiMovieResponse(
    val results: List<ApiMovie>,
    @SerialName("total_pages") val totalPages: Int,
)
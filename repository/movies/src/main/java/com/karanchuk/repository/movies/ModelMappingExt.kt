package com.karanchuk.repository.movies

import com.karanchuk.common.model.api.ApiConstants.POSTER_BASE_URL
import com.karanchuk.common.model.api.ApiMovie
import com.karanchuk.core.entity.movies.DbMovie
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.common.util.fromServerDateFormatString

fun DbMovie.toMovie() = Movie(
    id = id,
    title = title,
    posterUrl = "$POSTER_BASE_URL$posterUrl",
    voteAverage = voteAverage,
    releaseDate = releaseDate,
)
fun List<DbMovie>.toMovieList() = map { it.toMovie() }

fun ApiMovie.toDbMovie() = DbMovie(
    id = id,
    title = title,
    posterUrl = posterUrl.orEmpty(),
    voteAverage = voteAverage,
    releaseDate = releaseDate.fromServerDateFormatString(),
)
fun List<ApiMovie>.apiToDbMovieList() = map { it.toDbMovie() }
package com.karanchuk.movieviewer.repository.movies

import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import com.karanchuk.movieviewer.data.source.network.ApiConstants.POSTER_BASE_URL
import com.karanchuk.movieviewer.data.source.network.model.ApiMovie
import com.karanchuk.movieviewer.repository.movies.domain.Movie
import com.karanchuk.movieviewer.util.fromServerDateFormatString

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
    posterUrl = posterUrl,
    voteAverage = voteAverage,
    releaseDate = releaseDate.fromServerDateFormatString(),
)
fun List<ApiMovie>.apiToDbMovieList() = map { it.toDbMovie() }
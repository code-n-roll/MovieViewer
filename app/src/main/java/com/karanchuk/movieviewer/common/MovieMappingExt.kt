package com.karanchuk.movieviewer.common

import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import com.karanchuk.movieviewer.repository.movies.domain.Movie

fun Movie.toMovieCardState(): MovieCardState {
    return MovieCardState(
        id = id,
        title = title,
        posterUrl = posterUrl,
    )
}
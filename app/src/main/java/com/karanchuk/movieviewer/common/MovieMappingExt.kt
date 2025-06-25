package com.karanchuk.movieviewer.common

import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.repository.movies.domain.Movie

fun Movie.toMovieCardState(): MovieCardState {
    return MovieCardState(
        id = id,
        title = title,
        posterUrl = posterUrl,
    )
}

fun FeedType.toTitle(): String {
    return when (this) {
        FeedType.POPULAR -> "Popular"
        FeedType.NOW_PLAYING -> "Now playing"
        FeedType.TOP_RATED -> "Top rated"
        FeedType.UPCOMING -> "Upcoming"
    }
}
package com.karanchuk.movieviewer.common

import androidx.annotation.StringRes
import com.karanchuk.movieviewer.R
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

@StringRes
fun FeedType.toStringResId(): Int {
    return when (this) {
        FeedType.POPULAR -> R.string.movie_category_popular
        FeedType.NOW_PLAYING -> R.string.movie_category_now_playing
        FeedType.TOP_RATED -> R.string.movie_category_top_rated
        FeedType.UPCOMING -> R.string.movie_category_upcoming
    }
}
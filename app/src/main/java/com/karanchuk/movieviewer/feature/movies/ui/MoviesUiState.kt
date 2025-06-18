package com.karanchuk.movieviewer.feature.movies.ui

import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.feature.movies.ui.components.section.MovieSectionState

sealed interface MoviesUiState {

    data object Loading : MoviesUiState
    data object Error : MoviesUiState
    data class Content(
        val feeds: Map<FeedType, MovieSectionState>
    ) : MoviesUiState {

        companion object {
            val Preview = Content(
                feeds = mapOf(
                    FeedType.POPULAR to MovieSectionState.Preview,
                    FeedType.NOW_PLAYING to MovieSectionState.Preview,
                    FeedType.TOP_RATED to MovieSectionState.Preview,
                    FeedType.UPCOMING to MovieSectionState.Preview,
                ),
            )
        }
    }
}
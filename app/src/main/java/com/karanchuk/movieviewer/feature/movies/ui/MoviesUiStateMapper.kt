package com.karanchuk.movieviewer.feature.movies.ui

import com.karanchuk.movieviewer.common.toMovieCardState
import com.karanchuk.movieviewer.core.tea.component.UiStateMapper
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.feature.movies.ui.components.section.MovieSectionState
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.util.Lce
import com.karanchuk.movieviewer.util.requireContent

class MoviesUiStateMapper : UiStateMapper<MoviesState, MoviesUiState> {

    override fun map(state: MoviesState): MoviesUiState {
        return when (state.feeds) {
            is Lce.Content -> {
                MoviesUiState.Content(
                    feeds = state.feeds.requireContent().mapValues { (feedType, movies) ->
                        MovieSectionState(
                            title = feedType.toTitle(),
                            movies = movies.map { it.toMovieCardState() }
                        )
                    }
                )
            }

            is Lce.Error -> MoviesUiState.Error
            is Lce.Loading -> MoviesUiState.Loading
        }
    }

    private fun FeedType.toTitle(): String {
        return when (this) {
            FeedType.POPULAR -> "Popular"
            FeedType.NOW_PLAYING -> "Now playing"
            FeedType.TOP_RATED -> "Top rated"
            FeedType.UPCOMING -> "Upcoming"
        }
    }
}
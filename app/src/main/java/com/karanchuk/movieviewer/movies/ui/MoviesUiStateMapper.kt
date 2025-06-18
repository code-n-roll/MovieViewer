package com.karanchuk.movieviewer.movies.ui

import com.karanchuk.movieviewer.core.tea.component.UiStateMapper
import com.karanchuk.movieviewer.data.source.Movie
import com.karanchuk.movieviewer.data.source.local.model.FeedType
import com.karanchuk.movieviewer.movies.ui.components.section.MovieSectionState
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.movies.ui.components.card.MovieCardState
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

    private fun Movie.toMovieCardState(): MovieCardState {
        return MovieCardState(
            id = id,
            title = title,
            posterUrl = posterUrl,
        )
    }
}
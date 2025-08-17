package com.karanchuk.feature.movies

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.karanchuk.common.model.domain.FeedType
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.common.ui.R
import com.karanchuk.common.ui.card.MovieCardState
import com.karanchuk.repository.movies.domain.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {

    private val popularFlow = moviesRepository.getPagedMoviesFlow(FeedType.POPULAR)
        .map { pagingData -> pagingData.map { it.toMovieCardState() } }
        .cachedIn(viewModelScope)

    private val topRatedFlow = moviesRepository.getPagedMoviesFlow(FeedType.TOP_RATED)
        .map { pagingData -> pagingData.map { it.toMovieCardState() } }
        .cachedIn(viewModelScope)

    private val nowPlayingFlow = moviesRepository.getPagedMoviesFlow(FeedType.NOW_PLAYING)
        .map { pagingData -> pagingData.map { it.toMovieCardState() } }
        .cachedIn(viewModelScope)

    private val upcomingFlow = moviesRepository.getPagedMoviesFlow(FeedType.UPCOMING)
        .map { pagingData -> pagingData.map { it.toMovieCardState() } }
        .cachedIn(viewModelScope)

    private val sections by lazy {
        listOf(
            FeedType.POPULAR to popularFlow,
            FeedType.TOP_RATED to topRatedFlow,
            FeedType.NOW_PLAYING to nowPlayingFlow,
            FeedType.UPCOMING to upcomingFlow,
        ).map { (feedType, flow) -> feedType.toStringResId() to flow }
    }

    val state by lazy {
        MoviesScreenState(
            titleResId = R.string.screen_movies,
            sections = sections,
        )
    }

    @StringRes
    private fun FeedType.toStringResId(): Int {
        return when (this) {
            FeedType.POPULAR -> R.string.movie_category_popular
            FeedType.NOW_PLAYING -> R.string.movie_category_now_playing
            FeedType.TOP_RATED -> R.string.movie_category_top_rated
            FeedType.UPCOMING -> R.string.movie_category_upcoming
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
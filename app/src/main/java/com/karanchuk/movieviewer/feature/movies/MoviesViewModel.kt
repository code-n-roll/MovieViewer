package com.karanchuk.movieviewer.feature.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.karanchuk.movieviewer.common.toMovieCardState
import com.karanchuk.movieviewer.common.toStringResId
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
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

    val sections by lazy {
        listOf(
            FeedType.POPULAR to popularFlow,
            FeedType.TOP_RATED to topRatedFlow,
            FeedType.NOW_PLAYING to nowPlayingFlow,
            FeedType.UPCOMING to upcomingFlow,
        ).map { (feedType, flow) -> feedType.toStringResId() to flow }
    }
}
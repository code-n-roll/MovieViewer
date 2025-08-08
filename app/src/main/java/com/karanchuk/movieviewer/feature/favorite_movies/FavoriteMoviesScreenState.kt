package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import com.karanchuk.movieviewer.repository.favorite_movies.domain.DomainSort

@Immutable
data class FavoriteMoviesScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val movies: List<MovieCardState>,
    @StringRes val topBarTitle: Int,
    val selectedDomainSort: DomainSort,
) {
    companion object {
        val Empty = FavoriteMoviesScreenState(
            isLoading = false,
            isError = false,
            movies = emptyList(),
            topBarTitle = R.string.screen_favorites,
            selectedDomainSort = DomainSort.RELEASE_DATE_DESC
        )
        val Preview = FavoriteMoviesScreenState(
            isLoading = false,
            isError = false,
            movies = listOf(
                MovieCardState.Preview,
                MovieCardState.Preview,
                MovieCardState.Preview,
            ),
            topBarTitle = R.string.screen_favorites,
            selectedDomainSort = DomainSort.RELEASE_DATE_DESC,
        )
    }
}
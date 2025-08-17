package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.karanchuk.common.ui.R
import com.karanchuk.repository.favorite_movies.DomainSort

@Immutable
data class FavoriteMoviesScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val movies: List<com.karanchuk.common.ui.card.MovieCardState>,
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
                com.karanchuk.common.ui.card.MovieCardState.Preview,
                com.karanchuk.common.ui.card.MovieCardState.Preview,
                com.karanchuk.common.ui.card.MovieCardState.Preview,
            ),
            topBarTitle = R.string.screen_favorites,
            selectedDomainSort = DomainSort.RELEASE_DATE_DESC,
        )
    }
}
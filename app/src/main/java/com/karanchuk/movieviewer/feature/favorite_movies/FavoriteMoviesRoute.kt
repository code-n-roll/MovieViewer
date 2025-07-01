package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karanchuk.movieviewer.MovieViewerNavigationActions

@Composable
fun FavoriteMoviesRoute(
    vm: FavoriteMoviesViewModel,
    navActions: MovieViewerNavigationActions,
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    FavoriteMoviesScreen(
        state = state,
        onMovieDetailsClick = { movieId -> navActions.navigateToMovieDetails(movieId) },
        onSortSelected = { vm.onSortSelected(it) },
    )
}
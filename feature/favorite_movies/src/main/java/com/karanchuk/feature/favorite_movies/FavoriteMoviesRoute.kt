package com.karanchuk.feature.favorite_movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoriteMoviesRoute(
    vm: FavoriteMoviesViewModel,
    onMovieDetailsClick: (Int) -> Unit,
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    FavoriteMoviesScreen(
        state = state,
        onMovieDetailsClick = { movieId -> onMovieDetailsClick(movieId) },
        onSortSelected = { vm.onSortSelected(it) },
    )
}
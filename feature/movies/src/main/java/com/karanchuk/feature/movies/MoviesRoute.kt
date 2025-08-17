package com.karanchuk.feature.movies

import androidx.compose.runtime.Composable

@Composable
fun MoviesRoute(
    vm: MoviesViewModel,
    onMovieDetailsClick: (Int) -> Unit,
) {
    MoviesScreen(
        state = vm.state,
        onMovieDetailsClick = onMovieDetailsClick
    )
}
package com.karanchuk.movieviewer.feature.movies

import androidx.compose.runtime.Composable
import com.karanchuk.movieviewer.MovieViewerNavigationActions
import com.karanchuk.movieviewer.R

@Composable
fun MoviesRoute(
    vm: MoviesViewModel,
    navActions: MovieViewerNavigationActions
) {
    MoviesScreen(
        state = vm.state,
        onMovieDetailsClick = { movieId -> navActions.navigateToMovieDetails(movieId) }
    )
}
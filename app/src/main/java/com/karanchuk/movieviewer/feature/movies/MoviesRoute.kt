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
        titleResId = R.string.screen_movies,
        sections = vm.sections,
        onMovieDetailsClick = { movieId -> navActions.navigateToMovieDetails(movieId) }
    )
}
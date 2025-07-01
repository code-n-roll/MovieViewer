package com.karanchuk.movieviewer.feature.movie_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun MovieDetailsRoute(
    vm: MovieDetailsViewModel,
    movieId: Int,
    navController: NavHostController,
) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    MovieDetailsScreen(
        onBackClick = { navController.popBackStack() },
        onFavoriteClick = { vm.onFavoriteClick(movieId) },
        state = state,
    )
}
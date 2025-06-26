package com.karanchuk.movieviewer.feature.movie_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController

@Composable
fun MovieDetailsRoute(
    vm: MovieDetailsViewModel,
    movieId: Int,
    navController: NavHostController,
) {
    val state by vm.uiState.collectAsState()
    MovieDetailsScreen(
        onBackClick = { navController.popBackStack() },
        onFavoriteClick = { vm.onFavoriteClick(movieId) },
        state = state,
    )
}
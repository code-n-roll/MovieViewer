package com.karanchuk.movieviewer

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karanchuk.movieviewer.core.tea.compose.TeaComposable
import com.karanchuk.movieviewer.movies.MoviesScreen
import com.karanchuk.movieviewer.movies.MoviesViewModel
import com.karanchuk.movieviewer.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.movies.tea.core.MoviesUiEvent

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MovieViewerDestinations.MOVIES_ROUTE,
    navActions: MovieViewerNavigationActions = remember(navController) {
        MovieViewerNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(MovieViewerDestinations.MOVIES_ROUTE) {
            val vm: MoviesViewModel = hiltViewModel()

            TeaComposable(vm) { uiState ->
                val context = LocalContext.current

                EffectHandler { effect ->
                    when (effect) {
                        is MoviesEffect.ShowToast ->
                            Toast.makeText(context, "Hello world!", Toast.LENGTH_SHORT).show()

                        is MoviesEffect.OpenMovieDetails -> navActions.navigateToMovieDetails(effect.movieId)
                    }
                }

                MoviesScreen(
                    title = "[TODO] Movies",
                    state = uiState,
                    onItemClick = { movieId -> accept(MoviesUiEvent.OpenMovieDetails(movieId)) },
                    onFavoriteClick = { movieId -> accept(MoviesUiEvent.FavoriteClick(movieId)) },
                )
            }
        }
    }
}
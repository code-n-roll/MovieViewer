package com.karanchuk.movieviewer

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.karanchuk.common.ui.R
import com.karanchuk.movieviewer.MovieViewerDestinationsArgs.MOVIE_ID_ARG
import com.karanchuk.movieviewer.MovieViewerScreens.MOVIE_DETAILS_SCREEN

enum class BottomBarDestination(
    val route: String,
    @StringRes val stringResId: Int,
    val icon: ImageVector,
    val contentDescription: String
) {
    MOVIES(MovieViewerDestinations.MOVIES_ROUTE, R.string.screen_movies, Icons.Default.Home, "Movies"),
    FAVORITES(MovieViewerDestinations.FAVORITES_ROUTE, R.string.screen_favorites, Icons.Default.Favorite, "Favorite"),
    SETTINGS(MovieViewerDestinations.SETTINGS_ROUTE, R.string.screen_settings, Icons.Default.Settings, "Settings")
}

private object MovieViewerScreens {
    const val MOVIES_SCREEN = "movies"
    const val MOVIE_DETAILS_SCREEN = "movieDetails"
    const val FAVORITES_SCREEN = "favorites"
    const val SETTINGS_SCREEN = "settings"
}

object MovieViewerDestinationsArgs {
    const val MOVIE_ID_ARG = "movieId"
}

object MovieViewerDestinations {
    const val MOVIES_ROUTE = MovieViewerScreens.MOVIES_SCREEN
    const val MOVIE_DETAILS_ROUTE = "$MOVIE_DETAILS_SCREEN/{$MOVIE_ID_ARG}"
    const val SETTINGS_ROUTE = MovieViewerScreens.SETTINGS_SCREEN
    const val FAVORITES_ROUTE = MovieViewerScreens.FAVORITES_SCREEN
}

class MovieViewerNavigationActions(private val navController: NavController) {

    fun navigateToMovieDetails(movieId: Int) {
        navController.navigate("$MOVIE_DETAILS_SCREEN/$movieId")
    }
}
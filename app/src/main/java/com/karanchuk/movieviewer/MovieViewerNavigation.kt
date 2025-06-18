package com.karanchuk.movieviewer

import androidx.navigation.NavController
import com.karanchuk.movieviewer.MovieViewerDestinationsArgs.MOVIE_ID_ARG
import com.karanchuk.movieviewer.MovieViewerScreens.MOVIE_DETAILS_SCREEN

private object MovieViewerScreens {
    const val MOVIES_SCREEN = "movies"
    const val MOVIE_DETAILS_SCREEN = "movieDetails"
}

object MovieViewerDestinationsArgs {
    const val MOVIE_ID_ARG = "movieId"
}

object MovieViewerDestinations {
    const val MOVIES_ROUTE = MovieViewerScreens.MOVIES_SCREEN
    const val MOVIE_DETAILS_ROUTE = "$MOVIE_DETAILS_SCREEN/$MOVIE_ID_ARG"
}

class MovieViewerNavigationActions(private val navController: NavController) {

    fun navigateToMovieDetails(movieId: Int) {
        navController.navigate("$MOVIE_DETAILS_SCREEN/$movieId")
    }
}
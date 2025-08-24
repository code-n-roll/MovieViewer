package com.karanchuk.movieviewer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karanchuk.common.navigation.MovieViewerDestinationsArgs.MOVIE_ID_ARG
import com.karanchuk.feature.favorite_movies.FavoriteMoviesRoute
import com.karanchuk.feature.movie_details.MovieDetailsRoute
import com.karanchuk.feature.movies.MoviesRoute
import com.karanchuk.feature.settings.SettingsRoute

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MovieViewerDestinations.MOVIES_ROUTE,
    navActions: MovieViewerNavigationActions = remember(navController) {
        MovieViewerNavigationActions(navController)
    },
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            if (currentRoute in BottomBarDestination.entries.map { it.route }) {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                    BottomBarDestination.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = currentRoute == destination.route,
                            onClick = {
                                navController.navigate(route = destination.route) {
                                    popUpTo(BottomBarDestination.MOVIES.route)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = { Text(stringResource(destination.stringResId)) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(MovieViewerDestinations.MOVIES_ROUTE) { navBackStackEntry ->
                MoviesRoute(
                    vm = hiltViewModel(navBackStackEntry),
                    onMovieDetailsClick = { movieId -> navActions.navigateToMovieDetails(movieId) }
                )
            }
            composable(MovieViewerDestinations.FAVORITES_ROUTE) { navBackStackEntry ->
                FavoriteMoviesRoute(
                    vm = hiltViewModel(navBackStackEntry),
                    onMovieDetailsClick = { movieId ->
                        navActions.navigateToMovieDetails(movieId)
                    }
                )
            }
            composable(MovieViewerDestinations.SETTINGS_ROUTE) { navBackStackEntry ->
                SettingsRoute(vm = hiltViewModel(navBackStackEntry))
            }
            composable(
                route = MovieViewerDestinations.MOVIE_DETAILS_ROUTE,
                arguments = listOf(navArgument(MOVIE_ID_ARG) { type = NavType.IntType })
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getInt(MOVIE_ID_ARG)?.let { movieId ->
                    MovieDetailsRoute(
                        vm = hiltViewModel(navBackStackEntry),
                        navController = navController,
                        movieId = movieId,
                    )
                }
            }
        }
    }
}
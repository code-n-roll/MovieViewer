package com.karanchuk.movieviewer

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.karanchuk.movieviewer.core.tea.compose.TeaComposable
import com.karanchuk.movieviewer.feature.movies.MoviesScreen
import com.karanchuk.movieviewer.feature.movies.tea.MoviesViewModel
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesEffect
import com.karanchuk.movieviewer.feature.movies.tea.core.MoviesUiEvent

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
                            label = { Text(destination.label) }
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
            composable(MovieViewerDestinations.MOVIES_ROUTE) {
                val parentEntry = remember { navController.getBackStackEntry(MovieViewerDestinations.MOVIES_ROUTE) }
                val vm: MoviesViewModel = hiltViewModel(parentEntry)

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
                        title = "Movies",
                        state = uiState,
                        onItemClick = { movieId -> accept(MoviesUiEvent.OpenMovieDetails(movieId)) },
                        onFavoriteClick = { movieId -> accept(MoviesUiEvent.FavoriteClick(movieId)) },
                    )
                }
            }
            composable(MovieViewerDestinations.FAVORITES_ROUTE) {

            }
            composable(MovieViewerDestinations.SETTINGS_ROUTE) {

            }
            composable(MovieViewerDestinations.MOVIE_DETAILS_ROUTE) {

            }
        }
    }
}
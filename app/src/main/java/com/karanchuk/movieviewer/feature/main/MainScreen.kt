package com.karanchuk.movieviewer.feature.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karanchuk.movieviewer.NavGraph
import com.karanchuk.movieviewer.ui.theme.MovieViewerTheme

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val mainUiState = viewModel.uiState.collectAsStateWithLifecycle()

    MovieViewerTheme(
        selectedAppTheme = mainUiState.value.selectedTheme,
    ) {
        NavGraph()
    }
}
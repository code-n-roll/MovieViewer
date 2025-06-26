package com.karanchuk.movieviewer.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.karanchuk.movieviewer.NavGraph
import com.karanchuk.movieviewer.ui.theme.MovieViewerTheme

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val mainUiState = viewModel.uiState.collectAsState()

    MovieViewerTheme(
        selectedAppTheme = mainUiState.value.selectedTheme,
    ) {
        NavGraph()
    }
}
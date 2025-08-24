package com.karanchuk.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(
    content: @Composable (MainScreenUiState) -> Unit
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val mainUiState by viewModel.uiState.collectAsStateWithLifecycle()

    content(mainUiState)
}
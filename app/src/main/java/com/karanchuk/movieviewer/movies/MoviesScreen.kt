@file:OptIn(ExperimentalMaterial3Api::class)

package com.karanchuk.movieviewer.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karanchuk.movieviewer.movies.ui.MoviesUiState
import com.karanchuk.movieviewer.movies.ui.components.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    title: String,
    state: MoviesUiState,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(),
                windowInsets = WindowInsets(0.dp)
            )
        },
    ) { paddingValues ->
        when (state) {
            is MoviesUiState.Content -> Content(
                state = state,
                paddingValues = paddingValues,
                onItemClick = onItemClick,
                onFavoriteClick = onFavoriteClick,
            )
            is MoviesUiState.Error -> Error()
            is MoviesUiState.Loading -> Loading()
        }
    }
}

@Composable
private fun Error() {
    Text(text = "Error")
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Content(
    state: MoviesUiState.Content,
    paddingValues: PaddingValues,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.movies) { movie ->
            MovieCard(
                state = movie,
                onItemClick = onItemClick,
                onFavoriteClick = onFavoriteClick,
            )
        }
    }
}

@Preview
@Composable
fun MoviesScreenSuccessPreview() {
    MoviesScreen(
        title = "Movies",
        state = MoviesUiState.Content.Preview,
        onItemClick = {},
        onFavoriteClick = {},
    )
}

@Preview
@Composable
fun MoviesScreenLoadingPreview() {
    MoviesScreen(
        title = "Movies",
        state = MoviesUiState.Loading,
        onItemClick = {},
        onFavoriteClick = {},
    )
}

@Preview
@Composable
fun MoviesScreenErrorPreview() {
    MoviesScreen(
        title = "Movies",
        state = MoviesUiState.Error,
        onItemClick = {},
        onFavoriteClick = {},
    )
}
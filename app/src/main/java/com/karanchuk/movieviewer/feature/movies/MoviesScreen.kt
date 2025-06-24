@file:OptIn(ExperimentalMaterial3Api::class)

package com.karanchuk.movieviewer.feature.movies

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.feature.movies.ui.MoviesUiState
import com.karanchuk.movieviewer.feature.movies.ui.components.MovieScreenShimmer
import com.karanchuk.movieviewer.feature.movies.ui.components.section.MovieSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    @StringRes titleResId: Int,
    state: MoviesUiState,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(titleResId)) },
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
    MovieScreenShimmer()
}

@Composable
private fun Content(
    state: MoviesUiState.Content,
    paddingValues: PaddingValues,
    onItemClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(
            state.feeds.values.toList()
        ) {
            MovieSection(
                state = it,
                onItemClick = onItemClick,
            )
        }
    }
}

@Preview
@Composable
fun MoviesScreenSuccessPreview() {
    MoviesScreen(
        titleResId = R.string.screen_movies,
        state = MoviesUiState.Content.Preview,
        onItemClick = {},
        onFavoriteClick = {},
    )
}

@Preview
@Composable
fun MoviesScreenLoadingPreview() {
    MoviesScreen(
        titleResId = R.string.screen_movies,
        state = MoviesUiState.Loading,
        onItemClick = {},
        onFavoriteClick = {},
    )
}

@Preview
@Composable
fun MoviesScreenErrorPreview() {
    MoviesScreen(
        titleResId = R.string.screen_movies,
        state = MoviesUiState.Error,
        onItemClick = {},
        onFavoriteClick = {},
    )
}
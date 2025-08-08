@file:OptIn(ExperimentalMaterial3Api::class)

package com.karanchuk.movieviewer.feature.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karanchuk.movieviewer.feature.movies.ui.components.section.MovieSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    state: MoviesScreenState,
    onMovieDetailsClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(state.titleResId)) },
                colors = TopAppBarDefaults.topAppBarColors(),
                windowInsets = WindowInsets(0.dp)
            )
        },
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                count = state.sections.size,
                key = { state.sections[it].first }
            ) { index ->
                Text(
                    text = stringResource(state.sections[index].first),
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                MovieSection(
                    movies = state.sections[index].second,
                    onItemClick = onMovieDetailsClick
                )
            }
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesScreen(
        state = MoviesScreenState.Preview,
        onMovieDetailsClick = {}
    )
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.karanchuk.movieviewer.feature.movies

import androidx.annotation.StringRes
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
import androidx.paging.PagingData
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import com.karanchuk.movieviewer.feature.movies.ui.components.section.MovieSection
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    @StringRes titleResId: Int,
    sections: List<Pair<String, Flow<PagingData<MovieCardState>>>>,
    onMovieDetailsClick: (Int) -> Unit,
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
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                count = sections.size,
                key = { sections[it].first }
            ) { index ->
                Text(
                    text = sections[index].first,
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                MovieSection(
                    movies = sections[index].second,
                    onItemClick = onMovieDetailsClick
                )
            }
        }
    }
}

@Preview
@Composable
fun MoviesScreenSuccessPreview() {
    MoviesScreen(
        titleResId = R.string.screen_movies,
        sections = emptyList(),
        onMovieDetailsClick = {}
    )
}
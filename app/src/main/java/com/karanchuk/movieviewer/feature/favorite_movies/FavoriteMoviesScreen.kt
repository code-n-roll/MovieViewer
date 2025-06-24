package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCard
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import com.karanchuk.movieviewer.repository.favorite_movies.domain.DomainSort
import com.karanchuk.movieviewer.repository.favorite_movies.domain.toTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(
    state: FavoriteMoviesScreenState,
    onMovieDetailsClick: (Int) -> Unit,
    onSortSelected: (DomainSort) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Change sort")
                    }
                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        DomainSort.entries.forEach { option ->
                            DropdownMenuItem(
                                leadingIcon = { if (option == state.selectedDomainSort) Icon(Icons.Default.Check, contentDescription = "") },
                                text = { Text(text = stringResource(id = option.toTitle())) },
                                onClick = {
                                    onSortSelected(option)
                                    isExpanded = false
                                }
                            )
                        }
                    }
                },
                title = { Text(text = stringResource(state.topBarTitle)) },
                colors = TopAppBarDefaults.topAppBarColors(),
                windowInsets = WindowInsets(0.dp)
            )
        }
    ) { paddingValues ->
        when {
            state.isError -> Error()
            state.isLoading -> Loading()
            else -> Content(
                movies = state.movies,
                paddingValues = paddingValues,
                onMovieDetailsClick = onMovieDetailsClick,
            )
        }
    }
}

@Composable
fun Loading() {

}

@Composable
fun Error() {

}

@Composable
fun Content(
    movies: List<MovieCardState>,
    paddingValues: PaddingValues,
    onMovieDetailsClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        itemsIndexed(
            items = movies,
            key = { index, item -> if (index == 0) 0 else item.id }
        ) { _, movie ->
            MovieCard(
                modifier = Modifier.animateItem(),
                state = movie,
                onItemClick = onMovieDetailsClick
            )
        }
    }
}

@Preview
@Composable
fun FavoriteMoviesScreenPreview() {
    FavoriteMoviesScreen(
        state = FavoriteMoviesScreenState.Preview,
        onMovieDetailsClick = {},
        onSortSelected = {},
    )
}
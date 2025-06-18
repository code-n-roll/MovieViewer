@file:OptIn(ExperimentalMaterial3Api::class)

package com.karanchuk.movieviewer.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

data class MoviesScreenState(
    val title: String,
    val movies: List<MovieCardState> = emptyList(),
) {
    companion object {
        val Preview = MoviesScreenState(
            title = "Movies",
            movies = listOf(
                MovieCardState.Preview,
                MovieCardState.Preview,
                MovieCardState.Preview,
            )
        )
    }
}

data class MovieCardState(
    val id: Int,
    val title: String,
    val date: String,
    val vote: String,
    val posterUrl: String,
) {
    companion object {
        val Preview = MovieCardState(
            id = 0,
            title = "Movie 1",
            posterUrl = "https://picsum.photos/200/300",
            date = "2023-01-01",
            vote = "Vote Average: 8.0"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    state: MoviesScreenState = MoviesScreenState.Preview,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(text = state.title) },
                colors = TopAppBarDefaults.topAppBarColors(),
                windowInsets = WindowInsets(0.dp)
            )
        },
    ) { paddingValues ->
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
}

@Composable
fun MovieCard(
    state: MovieCardState,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(state.id) })
        ,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.posterUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(96.dp)
                    .width(64.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = state.title,
                    modifier = Modifier,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                )
                Text(
                    text = state.date,
                    modifier = Modifier
                )
                Text(
                    text = state.vote,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = { onFavoriteClick(state.id) }
            ) {
                Icon(
                    modifier = Modifier,
                    tint = androidx.compose.ui.graphics.Color.Red,
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
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
        onItemClick = {},
        onFavoriteClick = {},
    )
}
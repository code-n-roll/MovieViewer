package com.karanchuk.movieviewer.feature.movies.ui.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCard

@Composable
fun MovieSection(
    state: MovieSectionState,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = state.title,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium)
        )
        LazyRow(
            modifier = Modifier,
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.movies) { movie ->
                MovieCard(
                    state = movie,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}

@Preview
@Composable
fun SectionPreview() {
    MovieSection(
        state = MovieSectionState.Preview,
        onItemClick = {},
    )
}
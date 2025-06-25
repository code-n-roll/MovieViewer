package com.karanchuk.movieviewer.feature.movies.ui.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCard
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieSection(
    movies: Flow<PagingData<MovieCardState>>,
    onItemClick: (Int) -> Unit,
) {
    val pagingItems = movies.collectAsLazyPagingItems()

    LazyRow(
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
            contentType = pagingItems.itemContentType { it }
        ) { index ->
            pagingItems[index]?.let { movie ->
                MovieCard(
                    state = movie,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}
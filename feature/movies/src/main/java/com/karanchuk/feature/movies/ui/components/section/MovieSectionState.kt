package com.karanchuk.feature.movies.ui.components.section

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.karanchuk.common.ui.card.MovieCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@Stable
data class MovieSectionState(
    val movies: Flow<PagingData<com.karanchuk.common.ui.card.MovieCardState>>,
) {
    companion object {
        val Empty = MovieSectionState(
            movies = flowOf(PagingData.empty())
        )
        val Preview = MovieSectionState(
            movies = flow {
                PagingData.from(
                    listOf(
                        com.karanchuk.common.ui.card.MovieCardState.Preview,
                        com.karanchuk.common.ui.card.MovieCardState.Preview,
                        com.karanchuk.common.ui.card.MovieCardState.Preview,
                    )
                )
            }
        )
    }
}
package com.karanchuk.movieviewer.feature.movies.ui.components.section

import androidx.paging.PagingData
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

data class MovieSectionState(
    val movies: Flow<PagingData<MovieCardState>>,
) {
    companion object {
        val Empty = MovieSectionState(
            movies = flowOf(PagingData.empty())
        )
        val Preview = MovieSectionState(
            movies = flow {
                PagingData.from(
                    listOf(
                        MovieCardState.Preview,
                        MovieCardState.Preview,
                        MovieCardState.Preview,
                    )
                )
            }
        )
    }
}
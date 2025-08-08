package com.karanchuk.movieviewer.feature.movies

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.feature.movies.ui.components.card.MovieCardState
import kotlinx.coroutines.flow.Flow

@Stable
class MoviesScreenState(
    @StringRes val titleResId: Int,
    val sections: List<Pair<Int, Flow<PagingData<MovieCardState>>>>,
) {
    companion object {
        val Preview = MoviesScreenState(
            titleResId = R.string.screen_movies,
            sections = emptyList(),
        )
    }
}
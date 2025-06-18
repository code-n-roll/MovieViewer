package com.karanchuk.movieviewer.movies.ui

import com.karanchuk.movieviewer.core.tea.component.UiStateMapper
import com.karanchuk.movieviewer.data.source.Movie
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
import com.karanchuk.movieviewer.movies.ui.components.MovieCardState
import com.karanchuk.movieviewer.util.Lce
import com.karanchuk.movieviewer.util.requireContent
import java.time.format.DateTimeFormatter

data class MoviesWording(
    val screenTitle: String,
    val rating: String,
    val releaseDate: String,
) {
    companion object {
        val Preview = MoviesWording(
            screenTitle = "Movies",
            rating = "Rating: ",
            releaseDate = "Release date: ",
        )
    }
}

class MoviesUiStateMapper : UiStateMapper<MoviesState, MoviesUiState> {

    private val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_UI)

    override fun map(state: MoviesState): MoviesUiState {
        val wording = MoviesWording.Preview
        return when (state.movies) {
            is Lce.Content -> {
                MoviesUiState.Content(
                    movies = state.movies.requireContent().map { movie ->
                        movie.toMovieCardState(wording)
                    }
                )
            }

            is Lce.Error -> MoviesUiState.Error
            is Lce.Loading -> MoviesUiState.Loading
        }
    }

    private fun Movie.toMovieCardState(wording: MoviesWording): MovieCardState {
        return MovieCardState(
            id = id,
            title = title,
            posterUrl = posterUrl,
            date = "${wording.releaseDate} ${releaseDate.format(formatter)}",
            vote = "${wording.rating} ${voteAverage.toInt()}/$MAX_RATING",
        )
    }

    companion object {
        private const val MAX_RATING = 10
        private const val DATE_FORMAT_UI = "MMM dd, yyyy"
    }
}
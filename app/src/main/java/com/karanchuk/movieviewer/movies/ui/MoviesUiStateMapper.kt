package com.karanchuk.movieviewer.movies.ui

import com.karanchuk.movieviewer.core.tea.component.UiStateMapper
import com.karanchuk.movieviewer.movies.MovieCardState
import com.karanchuk.movieviewer.movies.MoviesScreenState
import com.karanchuk.movieviewer.movies.tea.core.MoviesState
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
        return MoviesUiState(
            moviesScreenState = MoviesScreenState(
                title = wording.screenTitle,
                movies = state.movies.map { movie ->
                    MovieCardState(
                        id = movie.id,
                        title = movie.title,
                        posterUrl = movie.posterUrl,
                        date = "${wording.releaseDate} ${movie.releaseDate.format(formatter)}",
                        vote = "${wording.rating} ${movie.voteAverage.toInt()}/10",
                    )
                }
            )
        )
    }

    companion object {
        private const val DATE_FORMAT_UI = "MMM dd, yyyy"
    }
}
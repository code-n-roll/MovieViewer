package com.karanchuk.movieviewer.movies.ui.components

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
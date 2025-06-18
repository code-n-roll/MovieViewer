package com.karanchuk.movieviewer.movies.ui.components.card

data class MovieCardState(
    val id: Int,
    val title: String,
    val posterUrl: String,
) {
    companion object {
        val Preview = MovieCardState(
            id = 0,
            title = "Movie 1",
            posterUrl = "https://picsum.photos/200/300",
        )
    }
}
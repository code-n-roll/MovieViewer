package com.karanchuk.common.ui.card

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
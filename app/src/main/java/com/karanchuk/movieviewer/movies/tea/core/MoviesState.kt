package com.karanchuk.movieviewer.movies.tea.core

import com.karanchuk.movieviewer.data.source.Movie
import com.karanchuk.movieviewer.util.Lce

data class MoviesState(
    val movies: Lce<List<Movie>> = Lce.initial(),
)
package com.karanchuk.movieviewer.movies.tea.core

import com.karanchuk.movieviewer.data.source.Movie
import com.karanchuk.movieviewer.data.source.local.model.FeedType
import com.karanchuk.movieviewer.util.Lce

data class MoviesState(
    val feeds: Lce<Map<FeedType, List<Movie>>> = Lce.initial(),
)
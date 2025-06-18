package com.karanchuk.movieviewer.feature.movies.tea.core

import com.karanchuk.movieviewer.repository.movies.domain.Movie
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.util.Lce

data class MoviesState(
    val feeds: Lce<Map<FeedType, List<Movie>>> = Lce.initial(),
)
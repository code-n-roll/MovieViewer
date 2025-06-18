package com.karanchuk.movieviewer.feature.movies.tea.core

sealed interface MoviesCommand {

    data object LoadMovies : MoviesCommand
}
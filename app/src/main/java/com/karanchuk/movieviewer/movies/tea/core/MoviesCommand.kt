package com.karanchuk.movieviewer.movies.tea.core

sealed interface MoviesCommand {

    data object LoadMovies : MoviesCommand
}
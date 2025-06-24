package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karanchuk.movieviewer.common.toMovieCardState
import com.karanchuk.movieviewer.repository.favorite_movies.domain.DomainSort
import com.karanchuk.movieviewer.repository.favorite_movies.domain.FavoriteMoviesRepository
import com.karanchuk.movieviewer.repository.movies.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) : ViewModel() {

    private val _sortOption = MutableStateFlow(FavoriteMoviesScreenState.Empty.selectedDomainSort)

    val uiState: StateFlow<FavoriteMoviesScreenState> =
        _sortOption.flatMapLatest { sortOption ->
            favoriteMoviesRepository.getFavoriteMovies(sortOption).map { movies ->
                FavoriteMoviesScreenState.Empty.copy(
                    movies = movies.map(Movie::toMovieCardState),
                    selectedDomainSort = sortOption
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoriteMoviesScreenState.Empty
        )

    fun onSortSelected(sortOption: DomainSort) {
        _sortOption.value = sortOption
    }
}
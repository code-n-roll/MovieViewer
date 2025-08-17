package com.karanchuk.movieviewer.feature.favorite_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.common.ui.card.MovieCardState
import com.karanchuk.repository.favorite_movies.DomainSort
import com.karanchuk.repository.favorite_movies.FavoriteMoviesRepository
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
                    movies = movies.map { it.toMovieCardState() },
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

    private fun Movie.toMovieCardState(): MovieCardState {
        return MovieCardState(
            id = id,
            title = title,
            posterUrl = posterUrl,
        )
    }
}
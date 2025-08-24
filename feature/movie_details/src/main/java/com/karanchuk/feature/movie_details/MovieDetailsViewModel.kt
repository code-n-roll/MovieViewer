package com.karanchuk.feature.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karanchuk.common.navigation.MovieViewerDestinationsArgs.MOVIE_ID_ARG
import com.karanchuk.repository.favorite_movies.FavoriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_ARG]) {
        "movieId is missing in savedStateHandle"
    }

    private val _uiState = MutableStateFlow(MovieDetailsUiState.Preview)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteMoviesRepository
                .isFavoriteFlow(movieId = movieId)
                .collect { isFavorite ->
                    _uiState.update {
                        it.copy(topBarState = it.topBarState.copy(isFavorite = isFavorite))
                    }
                }
        }
    }

    fun onFavoriteClick(movieId: Int) = viewModelScope.launch {
        favoriteMoviesRepository.toggleFavorite(movieId)
    }
}
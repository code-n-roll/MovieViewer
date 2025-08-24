package com.karanchuk.movieviewer.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karanchuk.common.ui.AppTheme
import com.karanchuk.repository.settings.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class MainScreenUiState(
    val selectedTheme: AppTheme = AppTheme.SYSTEM_DEFAULT
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch {
        settingsRepository.getThemePreferenceSelection()
            .onSuccess { data ->
                data.collectLatest { theme ->
                    _uiState.update { it.copy(selectedTheme = theme) }
                }
            }
            .onFailure { Timber.e(it) }
    }
}
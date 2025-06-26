package com.karanchuk.movieviewer.feature.settings

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.common.AppTheme
import com.karanchuk.movieviewer.di.USER_PREFERENCES_KEY_THEME
import com.karanchuk.movieviewer.repository.settings.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@StringRes
fun AppTheme.toStringRes(): Int {
    return when (this) {
        AppTheme.LIGHT -> R.string.app_theme_light
        AppTheme.DARK -> R.string.app_theme_dark
        AppTheme.SYSTEM_DEFAULT -> R.string.app_theme_system_default
    }
}

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsScreenState.Default)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.getThemePreferenceSelection().onSuccess { data ->
                data.collectLatest { selectedTheme ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            appThemeSettingState = _uiState.value.appThemeSettingState.copy(subtitle = selectedTheme.toStringRes()),
                            appThemeSettingDialogState = _uiState.value.appThemeSettingDialogState.copy(selectedTheme = selectedTheme)
                        )
                    }
                }
            }
        }
    }

    fun onChangeAppThemeClick(selection: AppTheme) = viewModelScope.launch {
        settingsRepository.savePreferenceSelection(USER_PREFERENCES_KEY_THEME, selection.ordinal)
    }
}
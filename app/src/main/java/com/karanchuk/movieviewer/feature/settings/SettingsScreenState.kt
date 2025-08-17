package com.karanchuk.movieviewer.feature.settings

import com.karanchuk.movieviewer.common.AppTheme
import com.karanchuk.movieviewer.feature.settings.component.setting.SettingState
import com.karanchuk.movieviewer.feature.settings.component.theme_dialog.AppThemeSettingDialogState
import com.karanchuk.common.ui.R

data class SettingsScreenState(
    val appThemeSettingState: SettingState,
    val appThemeSettingDialogState: AppThemeSettingDialogState,
    val languageSettingState: SettingState,
) {
    companion object {
        val Default = SettingsScreenState(
            appThemeSettingState = SettingState.AppTheme,
            appThemeSettingDialogState = AppThemeSettingDialogState(
                themes = listOf(
                    AppTheme.LIGHT to R.string.app_theme_light,
                    AppTheme.DARK to R.string.app_theme_dark,
                    AppTheme.SYSTEM_DEFAULT to R.string.app_theme_system_default
                ),
                selectedTheme = AppTheme.SYSTEM_DEFAULT,
                titleResId = R.string.change_theme_title
            ),
            languageSettingState = SettingState.Language
        )
    }
}
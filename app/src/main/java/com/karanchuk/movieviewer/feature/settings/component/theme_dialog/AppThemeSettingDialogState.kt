package com.karanchuk.movieviewer.feature.settings.component.theme_dialog

import androidx.annotation.StringRes
import com.karanchuk.movieviewer.common.AppTheme

data class AppThemeSettingDialogState(
    @StringRes val titleResId: Int,
    val themes: List<Pair<AppTheme, Int>>,
    val selectedTheme: AppTheme,
)
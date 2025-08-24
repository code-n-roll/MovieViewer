package com.karanchuk.feature.settings.component.theme_dialog

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.karanchuk.common.ui.AppTheme

@Immutable
data class AppThemeSettingDialogState(
    @StringRes val titleResId: Int,
    val themes: List<Pair<AppTheme, Int>>,
    val selectedTheme: AppTheme,
)
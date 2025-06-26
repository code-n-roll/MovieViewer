package com.karanchuk.movieviewer.feature.settings.component.setting

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.ui.graphics.vector.ImageVector
import com.karanchuk.movieviewer.R

data class SettingState(
    val imageVector: ImageVector,
    @StringRes val title: Int,
    @StringRes val subtitle: Int
) {
    companion object {
        val AppTheme = SettingState(
            imageVector = Icons.Rounded.Lightbulb,
            title = R.string.change_theme_title,
            subtitle = R.string.app_theme_system_default,
        )
    }
}
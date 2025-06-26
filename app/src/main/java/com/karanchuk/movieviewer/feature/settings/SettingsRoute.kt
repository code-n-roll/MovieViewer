package com.karanchuk.movieviewer.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.karanchuk.movieviewer.util.openAppLanguageSettingsAppCompat

@Composable
fun SettingsRoute(
    vm: SettingsScreenViewModel,
) {
    val uiState by vm.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(vm.effects) {
        vm.effects.collect { effect ->
            when (effect) {
                is SettingsScreenEffect.OpenAppInfo -> context.openAppLanguageSettingsAppCompat()
            }
        }
    }

    SettingsScreen(
        state = uiState,
        onChangeAppThemeClick = vm::onChangeAppThemeClick,
        onChangeLanguageClick = vm::onChangeLanguageClick,
    )
}
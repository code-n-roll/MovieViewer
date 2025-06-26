package com.karanchuk.movieviewer.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karanchuk.movieviewer.R
import com.karanchuk.movieviewer.common.AppTheme
import com.karanchuk.movieviewer.feature.settings.component.setting.Setting
import com.karanchuk.movieviewer.feature.settings.component.theme_dialog.AppThemeSettingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsScreenState,
    onChangeAppThemeClick: (AppTheme) -> Unit,
) {
    val showThemeDialog = remember { mutableStateOf(false) }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.screen_settings)) },
                windowInsets = WindowInsets(0.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Setting(
                state = state.appThemeSettingState,
                onClick = {
                    showThemeDialog.value = !showThemeDialog.value
                },
            )

            AppThemeSettingDialog(
                showThemeDialog = showThemeDialog.value,
                state = state.appThemeSettingDialogState,
                onDismissRequest = { showThemeDialog.value = false },
                onOptionSelected = onChangeAppThemeClick,
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        state = SettingsScreenState.Default,
        onChangeAppThemeClick = {}
    )
}
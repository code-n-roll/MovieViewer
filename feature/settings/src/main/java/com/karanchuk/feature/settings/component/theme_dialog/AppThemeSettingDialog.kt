package com.karanchuk.feature.settings.component.theme_dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.karanchuk.common.ui.AppTheme
import com.karanchuk.feature.settings.component.theme_dialog.setting_option.SettingOption

@Composable
fun AppThemeSettingDialog(
    showThemeDialog: Boolean,
    state: AppThemeSettingDialogState,
    onDismissRequest: () -> Unit,
    onOptionSelected: (AppTheme) -> Unit,
) {
    if (showThemeDialog) {
        Dialog(
            onDismissRequest = { onDismissRequest() }
        ) {
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(state.titleResId),
                        style = TextStyle(
                            fontSize = 22.sp
                        )
                    )

                    state.themes.forEach { (appTheme, themeName) ->
                        SettingOption(
                            optionText = themeName,
                            selectedOption = state.selectedTheme == appTheme,
                            onOptionSelected = {
                                onOptionSelected(appTheme)
                                onDismissRequest()
                            }
                        )
                    }
                }
            }
        }
    }
}
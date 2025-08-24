package com.karanchuk.repository.settings.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.karanchuk.common.ui.AppTheme
import com.karanchuk.repository.settings.SettingsRepositoryImpl
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun savePreferenceSelection(key: String, selection: Int)

    suspend fun getThemePreferenceSelection(): Result<Flow<AppTheme>>

    companion object {
        fun create(
            dataStore: DataStore<Preferences>
        ): SettingsRepository {
            return SettingsRepositoryImpl(dataStore)
        }

        const val USER_PREFERENCES_KEY_THEME = "user_preferences.key_theme"
    }
}


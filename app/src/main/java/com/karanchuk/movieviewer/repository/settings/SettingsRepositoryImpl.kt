package com.karanchuk.movieviewer.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.karanchuk.movieviewer.common.AppTheme
import com.karanchuk.movieviewer.di.USER_PREFERENCES_KEY_THEME
import com.karanchuk.movieviewer.repository.settings.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = selection
        }
    }

    override suspend fun getThemePreferenceSelection(): Result<Flow<AppTheme>> {
        return runCatching {
            dataStore.data.map { preferences ->
                val key = preferences[intPreferencesKey(USER_PREFERENCES_KEY_THEME)] ?: AppTheme.SYSTEM_DEFAULT.ordinal
                AppTheme.entries[key]
            }
        }
    }
}
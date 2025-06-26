package com.karanchuk.movieviewer.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

fun Context.openAppLanguageSettingsAppCompat() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        openAppLanguageSettings()
    } else {
        openAppInfo()
    }
}

fun Context.openAppInfo() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
        // if you’re calling from a non-Activity context:
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Context.openAppLanguageSettings() {
    val intent = Intent(Settings.ACTION_APP_LOCALE_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
        // if you’re calling from a non-Activity context:
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}
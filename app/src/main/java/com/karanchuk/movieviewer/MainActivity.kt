package com.karanchuk.movieviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.karanchuk.feature.main.MainScreen
import com.karanchuk.movieviewer.ui.theme.MovieViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MainScreen {
                MovieViewerTheme(
                    selectedAppTheme = it.selectedTheme,
                ) {
                    NavGraph()
                }
            }
        }
    }
}
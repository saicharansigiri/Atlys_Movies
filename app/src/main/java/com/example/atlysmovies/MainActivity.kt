package com.example.atlysmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.atlysmovies.ui.screens.MovieDetailScreen
import com.example.atlysmovies.ui.screens.MovieListScreen
import com.example.atlysmovies.ui.theme.AtlysMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtlysMoviesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieDetailScreen(innerPadding)
                }
            }
        }
    }
}
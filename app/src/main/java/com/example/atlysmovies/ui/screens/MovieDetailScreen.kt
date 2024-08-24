package com.example.atlysmovies.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.ui.components.AppBar
import com.example.atlysmovies.ui.components.MovieDescription
import com.example.atlysmovies.ui.components.MovieDetailPoster

@Composable
fun MovieDetailScreen(movie: Movie, paddingValues: PaddingValues, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        AppBar(onBackClick)
        Spacer(modifier = Modifier.height(24.dp))
        MovieDetailPoster(movie.poster)
        Spacer(modifier = Modifier.height(24.dp))
        MovieDescription(text = movie.title)
    }
}

@Composable
@Preview
fun PreviewMovieDetailScreen() {
    val paddingValues = PaddingValues(16.dp)
    val movie = Movie("Doctor Strange", "https://www.omdbapi.com/src/poster.jpg", "")
    MovieDetailScreen(movie, paddingValues) {}
}
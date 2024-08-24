package com.example.atlysmovies.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.ui.components.MovieCard
import com.example.atlysmovies.ui.components.SearchBar
import com.example.atlysmovies.viewModel.MainViewModel
import com.example.atlysmovies.viewModel.MoviesUIState

@Composable
fun MovieListScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    onMovieClick: (movie: Movie) -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is MoviesUIState.HasMovies -> {
            ShowMoviesGrid(
                paddingValues,
                onMovieClick,
                (uiState.value as MoviesUIState.HasMovies).movies
            )
        }

        MoviesUIState.Loading -> {
            ShowLoading(paddingValues)
        }

        is MoviesUIState.NoMovies -> {
            val error = (uiState.value as MoviesUIState.NoMovies).error
            if (error.isEmpty()) {
                ShowNoMovies(paddingValues)
            } else {
                ShowError(error, paddingValues)
            }
        }
    }
}

@Composable
fun ShowMoviesGrid(
    paddingValues: PaddingValues,
    onMovieClick: (movie: Movie) -> Unit,
    movies: List<Movie>
) {
    val gridState = rememberLazyGridState()
    Column(modifier = Modifier.padding(paddingValues)) {
        Spacer(modifier = Modifier.height(24.dp))
        SearchBar()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), state = gridState,
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.size) { index ->
                MovieCard(movies[index]) {
                    onMovieClick.invoke(movies[index])
                }
            }
        }
    }
}

@Composable
fun ShowError(error: String, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Error: $error")
    }
}

@Composable
fun ShowNoMovies(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No Movies Found")
    }
}


@Composable
fun ShowLoading(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}
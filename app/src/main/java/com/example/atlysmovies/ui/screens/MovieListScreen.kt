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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.errorutils.ErrorResponse
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

    Column(modifier = Modifier.padding(paddingValues)) {
        Spacer(modifier = Modifier.height(24.dp))
        ShowMovies(
            uiState.value,
            onMovieClick,
            retry = { viewModel.retryFetchMovies() },
            onSearchUpdate = { viewModel.updateSearchQuery(it) },
        )

    }

}

@Composable
fun ShowMovies(
    uiState: MoviesUIState,
    onMovieClick: (movie: Movie) -> Unit,
    retry: () -> Unit,
    onSearchUpdate: (String) -> Unit
) {

    when (uiState) {
        is MoviesUIState.HasMovies -> {
            SearchBar() {
                onSearchUpdate.invoke(it)
            }
            ShowMoviesGrid(
                onMovieClick,
                uiState.movies
            )
        }

        MoviesUIState.Loading -> {
            ShowLoading()
        }

        is MoviesUIState.NoMovies -> {
            val error = uiState.error
            if (error.errorMsg.isEmpty()) {
                ShowNoMovies()
            } else {
                ShowError(error) {
                    retry.invoke()
                }
            }
        }
    }
}

@Composable
fun ShowMoviesGrid(
    onMovieClick: (movie: Movie) -> Unit,
    movies: List<Movie>,
) {
    val gridState = rememberLazyGridState()
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

@Composable
fun ShowError(error: ErrorResponse, retry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = error.errorMsg)
        if (error.retry) {
            Button(onClick = { retry.invoke() }) {
                Text(text = "Retry")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ShowNoMovies() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No Movies Found")
    }
}


@Composable
fun ShowLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}
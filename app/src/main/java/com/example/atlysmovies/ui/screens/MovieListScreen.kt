package com.example.atlysmovies.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atlysmovies.ui.components.MovieCard
import com.example.atlysmovies.ui.components.SearchBar

@Composable
fun MovieListScreen(paddingValues: PaddingValues) {
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

            items(100) { index ->
                MovieCard()
            }
        }
    }
}


@Composable
@Preview
fun PreviewMovieListScreen() {
    val paddingValues = PaddingValues(16.dp)
    MovieListScreen(paddingValues)
}
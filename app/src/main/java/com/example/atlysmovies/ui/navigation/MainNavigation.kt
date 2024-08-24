package com.example.atlysmovies.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.ui.screens.MovieDetailScreen
import com.example.atlysmovies.ui.screens.MovieListScreen
import com.example.atlysmovies.viewModel.MainViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.reflect.typeOf


@Composable
fun MainNavigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MoviesListScreenRoute
    ) {
        composable<MoviesListScreenRoute> {
            val viewModel = hiltViewModel<MainViewModel>()
            LaunchedEffect(Unit) {
                viewModel.fetchMovieList("Spider Man")
            }
            MovieListScreen(paddingValues, viewModel) { movie ->
                val encodedPosterUrl =
                    URLEncoder.encode(movie.poster, StandardCharsets.UTF_8.toString())
                val encodedMovie = movie.copy(
                    poster = encodedPosterUrl
                )
                navController.navigate(MovieDetailScreenRoute(encodedMovie))
            }
        }

        composable<MovieDetailScreenRoute>(
            typeMap = mapOf(typeOf<Movie>() to serializableType<Movie>())
        ) {
            val movie = it.toRoute<MovieDetailScreenRoute>().movie
            MovieDetailScreen(movie, paddingValues) {
                navController.popBackStack()
            }
        }
    }
}
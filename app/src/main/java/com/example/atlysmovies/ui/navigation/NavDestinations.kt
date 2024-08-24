package com.example.atlysmovies.ui.navigation

import com.example.atlysmovies.data.model.Movie
import kotlinx.serialization.Serializable


@Serializable
object MoviesListScreenRoute

@Serializable
data class MovieDetailScreenRoute(val movie: Movie)
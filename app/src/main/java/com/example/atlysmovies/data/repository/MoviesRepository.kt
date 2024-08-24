package com.example.atlysmovies.data.repository

import com.example.atlysmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesList(): Flow<List<Movie>>
}
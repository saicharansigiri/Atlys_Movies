package com.example.atlysmovies.data.repository

import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.utils.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesList(): Flow<Result<List<Movie>>>
}
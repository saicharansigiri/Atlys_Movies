package com.example.atlysmovies.data.repository

import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.network.OmdbApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepoImp @Inject constructor(private val omdbApiService: OmdbApiService) :
    MoviesRepository {

    override fun getMoviesList(searchQuery: String): Flow<List<Movie>> {
        return flow {
            try {
                val response = omdbApiService.getMovieDetails(searchQuery)
                emit(response.list)
            } catch (e: HttpException) {
                throw IOException("HTTP Error: ${e.message()}")
            }
        }
    }
}
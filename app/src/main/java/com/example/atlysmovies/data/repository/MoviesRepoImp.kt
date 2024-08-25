package com.example.atlysmovies.data.repository

import com.example.atlysmovies.utils.Result
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.network.OmdbApiService
import com.example.atlysmovies.errorutils.ErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepoImp @Inject constructor(private val omdbApiService: OmdbApiService) :
    MoviesRepository {

    companion object {
        private const val TAG = "MoviesRepoImp"
        private val description = """
             During World War II, Lt. Gen. Leslie Groves Jr. appoints physicist J. Robert Oppenheimer to work on the top-secret Manhattan Project. Oppenheimer and a team of scientists spend years developing and designing the atomic bomb. Their work comes to fruition on July 16, 1945, as they witness the world's first nuclear explosion, forever changing the course of history.
            """.trimIndent()

    }

    override fun getMoviesList(): Flow<Result<List<Movie>>> {
        return flow {
            try {
                val searchQuery = "Spider Man"
                val response = omdbApiService.getMovieDetails(searchQuery)
                val listWithDescription = response.list.map { movie ->
                    movie.copy(description = description)
                }
                emit(Result.Success(listWithDescription))
            } catch (e: Exception) {
                val errorResponse = ErrorHandler.handleException(e)
                emit(Result.Error(errorResponse))
            }
        }
    }
}
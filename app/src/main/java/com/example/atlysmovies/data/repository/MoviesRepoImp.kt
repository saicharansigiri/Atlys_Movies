package com.example.atlysmovies.data.repository

import android.util.Log
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.network.OmdbApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepoImp @Inject constructor(private val omdbApiService: OmdbApiService) :
    MoviesRepository {

    override fun getMoviesList(): Flow<List<Movie>> {
        return flow {
            try {
                val searchQuery = "Spider Man"
                val response = omdbApiService.getMovieDetails(searchQuery)
                val listWithDescription = response.list.map { movie ->
                    val description = """
                        During World War II, Lt. Gen. Leslie Groves Jr. appoints physicist J. Robert Oppenheimer to work on the top-secret Manhattan Project. Oppenheimer and a team of scientists spend years developing and designing the atomic bomb. Their work comes to fruition on July 16, 1945, as they witness the world's first nuclear explosion, forever changing the course of history.
                    """.trimIndent()
                    movie.copy(description = description)
                }
                emit(listWithDescription)
            } catch (e: HttpException) {
                throw IOException("HTTP Error: ${e.message()}")
            }
        }
    }
}
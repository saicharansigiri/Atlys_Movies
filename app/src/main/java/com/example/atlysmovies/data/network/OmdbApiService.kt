package com.example.atlysmovies.data.network

import com.example.atlysmovies.data.model.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {

    companion object {
        const val SEARCH_QUERY = "s"
    }


    @GET("/")
    suspend fun getMovieDetails(
        @Query(SEARCH_QUERY) searchQuery: String,
    ): MoviesListResponse
}
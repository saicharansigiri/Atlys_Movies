package com.example.atlysmovies.di

import com.example.atlysmovies.data.network.OmdbApiService
import com.example.atlysmovies.data.repository.MoviesRepoImp
import com.example.atlysmovies.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesRepoModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(apiService: OmdbApiService): MoviesRepository {
        return MoviesRepoImp(apiService)
    }
}
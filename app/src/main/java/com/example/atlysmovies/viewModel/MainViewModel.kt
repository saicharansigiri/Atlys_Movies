package com.example.atlysmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _movieDetails = MutableStateFlow<List<Movie>>(emptyList())
    val movieDetails: StateFlow<List<Movie>?> = _movieDetails

    fun fetchMovieList(searchQuery: String) {
        viewModelScope.launch {
            repository.getMoviesList(searchQuery)
                .catch { e ->
                    e.printStackTrace() // Handle errors
                }
                .collect { response ->
                    _movieDetails.value = response
                }
        }
    }

}
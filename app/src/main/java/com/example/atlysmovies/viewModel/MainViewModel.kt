package com.example.atlysmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUIState>(MoviesUIState.NoMovies())
    val uiState: StateFlow<MoviesUIState> = _uiState

    private var originalMoviesList: List<Movie> = emptyList()


    fun fetchMovieList() {
        viewModelScope.launch {
            _uiState.emit(MoviesUIState.Loading)
            repository.getMoviesList()
                .catch { e ->
                    _uiState.emit(MoviesUIState.NoMovies(error = e.message.toString()))
                    e.printStackTrace()
                }
                .collect { response ->
                    originalMoviesList = response
                    _uiState.emit(MoviesUIState.HasMovies(response))
                }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _uiState.emit(MoviesUIState.Loading)
            if (query.isEmpty()) {
                _uiState.emit(MoviesUIState.HasMovies(originalMoviesList))
            } else {
                val filteredMovies = originalMoviesList.filter {
                    it.title.contains(query, ignoreCase = true)
                }
                _uiState.emit(MoviesUIState.HasMovies(filteredMovies))
            }
        }
    }

}


sealed class MoviesUIState {
    data class NoMovies(val error: String = "") : MoviesUIState()
    data class HasMovies(val movies: List<Movie>, val filteredList: List<Movie> = emptyList()) :
        MoviesUIState()

    data object Loading : MoviesUIState()
}
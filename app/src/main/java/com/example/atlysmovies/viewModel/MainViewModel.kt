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

    private val _uiState = MutableStateFlow<MoviesUIState>(MoviesUIState.NoMovies())
    val uiState: StateFlow<MoviesUIState> = _uiState

    fun fetchMovieList(searchQuery: String) {
        viewModelScope.launch {
            _uiState.emit(MoviesUIState.Loading)
            repository.getMoviesList(searchQuery)
                .catch { e ->
                    _uiState.emit(MoviesUIState.NoMovies(error = e.message.toString()))
                    e.printStackTrace()
                }
                .collect { response ->
                    _uiState.emit(MoviesUIState.HasMovies(response))
                }
        }
    }

}


sealed class MoviesUIState {
    data class NoMovies(val error: String = "") : MoviesUIState()
    data class HasMovies(val movies: List<Movie>) : MoviesUIState()
    data object Loading : MoviesUIState()
}
package com.example.atlysmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlysmovies.data.model.Movie
import com.example.atlysmovies.data.repository.MoviesRepository
import com.example.atlysmovies.errorutils.ErrorResponse
import com.example.atlysmovies.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUIState>(MoviesUIState.NoMovies())
    val uiState: StateFlow<MoviesUIState> = _uiState

    private var originalMoviesList: List<Movie> = emptyList()

    private val searchQuery = MutableStateFlow<String?>(null)


    init {
        viewModelScope.launch {
            searchQuery
                .debounce(400)
                .distinctUntilChanged()
                .collect { query ->
                    query?.let{
                        searchMovies(it)
                    }
                }
        }
    }

    fun retryFetchMovies() {
        fetchMovieList()
    }

    fun fetchMovieList() {
        viewModelScope.launch {
            _uiState.emit(MoviesUIState.Loading)
            repository.getMoviesList()
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { response ->
                    when (response) {
                        is Result.Success -> {
                            originalMoviesList = response.data
                            _uiState.emit(MoviesUIState.HasMovies(response.data))
                        }

                        is Result.Error -> {
                            _uiState.emit(MoviesUIState.NoMovies(response.errorResponse))
                        }
                    }
                }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    private suspend fun searchMovies(query: String) {
        _uiState.emit(MoviesUIState.Loading)
        if(query.isEmpty()){
            _uiState.emit(MoviesUIState.HasMovies(originalMoviesList))
        }else{
            val filteredMovies = originalMoviesList.filter {
                it.title.contains(query, ignoreCase = true)
            }
            _uiState.emit(MoviesUIState.HasMovies(filteredMovies))
        }
    }

}


sealed class MoviesUIState {
    data class NoMovies(val error: ErrorResponse = ErrorResponse("", false)) : MoviesUIState()
    data class HasMovies(val movies: List<Movie>) : MoviesUIState()
    data object Loading : MoviesUIState()
}
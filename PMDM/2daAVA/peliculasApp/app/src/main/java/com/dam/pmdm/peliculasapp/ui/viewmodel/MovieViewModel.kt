package com.dam.pmdm.peliculasapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.pmdm.peliculasapp.repository.MovieRepo
import com.dam.pmdm.peliculasapp.repository.MovieRepoInterface
import com.dam.pmdm.peliculasapp.ui.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel : ViewModel() {
    private val _movies = MutableStateFlow<DataState<List<Movie>>>(DataState.loading)
    val movies: StateFlow<DataState<List<Movie>>> = _movies
    private val _addMovieResult = MutableStateFlow(Result.success(false))
    private val movieRepository: MovieRepoInterface = MovieRepo()
    fun getAllMovies() {
        viewModelScope.launch (Dispatchers.IO) {
            _movies.value = DataState.loading
            try {
                movieRepository.getAllMovies().collect { dataState ->
                    when(dataState){
                        is DataState.Success -> {
                            _movies.value = dataState
                        }
                        is DataState.Error -> {
                            _movies.value = dataState
                        }
                        is DataState.loading -> {}
                    }

                }
            }catch (e: Exception){
                _movies.value = DataState.Error(e)
            }
        }
    }

    fun addMovie(title: String, director: String, year: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieRepository.addMovie(
                    title = title,
                    director = director,
                    year = year,
                    callback = {isSuccess -> _addMovieResult.value = Result.success(isSuccess)}
                )
            }catch (e: Exception){
                _addMovieResult.value = Result.failure(e)
            }
        }
    }
}

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object loading : DataState<Nothing>()
}
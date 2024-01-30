package com.dam.movies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dam.movies.ui.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception

class MovieViewModel: ViewModel() {
    private val _movies = MutableStateFlow<DataState<List<Movie>>>(DataState.Loading)
    val movies: StateFlow<DataState<List<Movie>>> = _movies
    private val _addMovieResult = MutableStateFlow(Result.success(false))

    fun getAllMovies() {}

    fun addMovie(title: String, director: String, year: String){}
}

sealed class DataState <out T> {
    data class Success<T> (val data: T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}
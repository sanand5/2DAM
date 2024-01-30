package com.dam.movies.data.repository

import com.dam.movies.ui.model.Movie
import com.dam.movies.ui.viewmodel.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepoInterface {
    fun getAllMovies(): Flow<DataState<List<Movie>>>
    fun addMovie(title: String, director: String, year: String, callback: (Boolean)-> Unit)
}
package com.dam.pmdm.peliculasapp.repository

import com.dam.pmdm.peliculasapp.ui.model.Movie
import com.dam.pmdm.peliculasapp.ui.viewmodel.DataState
import kotlinx.coroutines.flow.Flow

interface




MovieRepoInterface {
    fun getAllMovies(): Flow<DataState<List<Movie>>>
    fun addMovie(title: String, director: String, year: String, callback: (Boolean)-> Unit)
}
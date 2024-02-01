package com.dam.pmdm.peliculasapp.repository

import com.dam.pmdm.peliculasapp.ui.model.Movie
import com.dam.pmdm.peliculasapp.ui.viewmodel.DataState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepo : MovieRepoInterface {
    private val db = FirebaseFirestore.getInstance();

    override fun getAllMovies(): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.loading)

        emit(DataState.Success(listOf<Movie>()))
    }.flowOn(Dispatchers.IO)

    override fun addMovie(
        title: String,
        director: String,
        year: String,
        callback: (Boolean) -> Unit
    ) {
        val movie = hashMapOf(
            "title" to title,
            "director" to director,
            "year" to year
        )
        //TODO
    }
}
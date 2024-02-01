package com.dam.pmdm.peliculasapp.repository

import android.util.Log
import com.dam.pmdm.peliculasapp.ui.model.Movie
import com.dam.pmdm.peliculasapp.ui.viewmodel.DataState
import com.dam.pmdm.peliculasapp.util.FireStoreCollections
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MovieRepo : MovieRepoInterface {
    private val db = FirebaseFirestore.getInstance();
    private val logTag = "MovieRepo/getAllMovies"

    override fun getAllMovies(): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.loading)
        try {
            val response = db.collection(FireStoreCollections.MOVIE).get().await()
            val moviesList = mutableListOf<Movie>()
            for (document in response.documents) {
                val movie = document.data?.let { mapMovieFromFirestore(it) }
                if (movie != null) {
                    moviesList.add(movie)
                }
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error getting document: ", e)
        }

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
        db.collection(FireStoreCollections.MOVIE).document(title).set(movie)
            .addOnSuccessListener {
                Log.i(logTag, "Movie uploaded to collection")
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.w(logTag, "Error witing document into collection")
                callback(false)

            }
        //TODO
    }
}

private fun mapMovieFromFirestore(movieData: Map<String, Any>): Movie {
    val title = movieData["title"] as? String
    val director = movieData["director"] as? String
    val year = movieData["year"] as? String
    return Movie(title ?: "", director ?: "", year ?: "")
}
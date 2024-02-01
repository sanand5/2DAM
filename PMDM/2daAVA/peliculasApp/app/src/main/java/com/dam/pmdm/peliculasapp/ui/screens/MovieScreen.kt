package com.dam.pmdm.peliculasapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.dam.pmdm.peliculasapp.ui.model.Movie
import com.dam.pmdm.peliculasapp.ui.viewmodel.DataState
import com.dam.pmdm.peliculasapp.ui.viewmodel.MovieViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(onAddMovie: () -> Unit, onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    // Logout icon button
                    IconButton(onClick = { onLogout() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                }, title = { Text(text = "Movies") }
            )
        },
    ) {
        MovieBodyContent(onAddMovie)
    }
}

@Composable
fun MovieBodyContent(onAddMovie: () -> Unit) {
    val logTag  = "MovieScreen"
    val movieViewModel: MovieViewModel = MovieViewModel()
    val movieState by movieViewModel.movies.collectAsState()
    movieViewModel.getAllMovies()

    //Movie list
    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(30.dp))
        }
        item {

            FloatingActionButton(onClick = {
                onAddMovie()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "FAB button to add movie"
                )
            }
        }
        items(items = listOf(Unit)) {
            when (val state = movieState){
                is DataState.Success -> {
                    state.data.forEach { movie ->
                        MovieItem(movie)
                    }
                }
                is DataState.Error -> {
                    Log.e(logTag,"Error: ${state.exception.message}")
                }
                is DataState.loading -> {
                    Log.e(logTag,"Loading...")
                }
            }
        }
    }
}
@Composable
fun MovieItem(movie: Movie){
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .background(MaterialTheme.colorScheme.surface)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(text = "Title: ${movie.title}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Director: ${movie.director}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Year: ${movie.year}")

        }
    }

}
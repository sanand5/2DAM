package com.dam.movies.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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

        }
    }
}
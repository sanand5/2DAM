package com.dam.pmdm.peliculasapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
package com.dam.movies.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreen(closeAddCompleteMovie:() ->Unit, ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Movie") },
                actions = {
                    // Logout icon button
                    IconButton(onClick = { closeAddCompleteMovie() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back")
                    }
                }
            )
        },
    ) {
        AddMovieScreenBodyContent(closeAddCompleteMovie)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreenBodyContent(closeAddCompleteMovie:() ->Unit) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.padding(30.dp))
        }
            item {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
        }
        item {
            TextField(
                value = director,
                onValueChange = { director = it },
                label = { Text("director") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            TextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("year") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            FloatingActionButton(onClick = {
                if (title.isNotEmpty() && director.isNotEmpty() && year.isNotEmpty()) {
                    closeAddCompleteMovie()
                }
                 }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "+")
            }
        }
    }
}
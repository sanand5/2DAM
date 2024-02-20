package com.dam.pmdm.examen.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.dam.pmdm.examen.logic.estaciones
import com.dam.pmdm.examen.logic.index
import com.dam.pmdm.examen.logic.like
import com.dam.pmdm.examen.logic.restarIndice
import com.dam.pmdm.examen.logic.showToast
import com.dam.pmdm.examen.logic.sumarIndice

@Composable
fun firsScreen(): Unit {
    firsScreenBodyContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun firsScreenBodyContent(): Unit {
    var name by rememberSaveable { mutableStateOf("") }
    val loc = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = estaciones.get(index.value).id),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {

                    like.value++
                    showToast("+1", loc)
                })
            )
        }
        item {
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null)
            Text(
                text = like.value.toString()
            )
        }
        item {
            Text(text = estaciones.get(index.value).nombre)
        }
        item {
            Button(onClick = { restarIndice() }) {
                Text(text = "Previous")
            }
            Button(onClick = { sumarIndice() }) {
                Text(text = "Next")
            }
        }
        item{
            TextField(
                value = name,
                onValueChange = { name = it }
            )
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomEnd)
    ) {
        FloatingActionButton(
            onClick = {
                like.value++
                showToast("+1", loc)
            }
        ) {
            Text(text = "+1")
        }
    }
}
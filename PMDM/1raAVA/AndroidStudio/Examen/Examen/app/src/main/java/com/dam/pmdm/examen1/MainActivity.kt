package com.dam.pmdm.examen1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dam.pmdm.examen1.ui.theme.Examen1Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Examen1Theme {
        Greeting("Android")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LazyColumn(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item {
            //TODO: Porque no se alinea el texto a la derecha
            Text(text = "Hola", textAlign = TextAlign.End)
        }
        item {
            Image(painter = painterResource(id = R.drawable.a1),
                contentDescription = "HolaCaracola",
                modifier = Modifier
                    .padding(15.dp)
                    .height(150.dp)

            )
        }
        item {
            var text by remember { mutableStateOf(TextFieldValue(text = "")) }
                OutlinedTextField(
                    value = text,
                    onValueChange = {newtext -> text = newtext},
                    label = {Text(text = "sad", color = Color.Black)},
                    placeholder = {Text("ph")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    leadingIcon = {Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "email")},
                    trailingIcon = {Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "email")}
                )
        }
        item {
            //TODO: poner favbutn en la derecha
            val Context = LocalContext.current
            FloatingActionButton(
                onClick = {
                    Toast.makeText(Context, "Hola", Toast.LENGTH_SHORT).show()
                },
                containerColor = colorResource(id = R.color.teal_700),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(fontSize = 24.sp, text = "+")
            }
        }}
    }
}

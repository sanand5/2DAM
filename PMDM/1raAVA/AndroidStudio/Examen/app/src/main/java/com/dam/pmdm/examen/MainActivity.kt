package com.dam.pmdm.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.pmdm.examen.ui.theme.ExamenTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    app()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamenTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
fun app(){
    LazyColumn(
        modifier = Modifier.background(Color.White)
    ){
        item {
            val img =
        }
    }
}
class imaguenes{
    @Composable
    fun winter(): Unit {
        Image(painter = painterResource(id = R.drawable.winter),
            contentDescription = R.string.winter_txt,
            modifier = Modifier
                .padding(15.dp)
                .height(150.dp)
        )
    }
    @Composable
    fun summer(): Unit {
        Image(painter = painterResource(id = R.drawable.summer),
            contentDescription = "Summer",
            modifier = Modifier
                .padding(15.dp)
                .height(150.dp)
        )
    }
}

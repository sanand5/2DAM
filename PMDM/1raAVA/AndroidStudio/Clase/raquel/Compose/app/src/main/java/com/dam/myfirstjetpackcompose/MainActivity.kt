package com.dam.myfirstjetpackcompose

import android.annotation.SuppressLint
import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.myfirstjetpackcompose.ui.theme.MyFirstJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //App()
                    //LazyColumnExample()
                    //SimplesTextField()
                    BasicScaffold()
                }
            }
        }


    }
}

////////////////////////////////////////////
//////////// PREVIEW COLUMN/ROW EXAMPLE //////////
////////////////////////////////////////////
@Composable
fun ColumnRowExample(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Item 1", fontSize = 20.sp, color = Color.Blue)
        Text(text = "Item 2", fontSize = 20.sp, color = Color.Green)
        Text(text = "Item 3", fontSize = 20.sp, color = Color.Red)
    }
    Column {
        Text(text = "Item 4", fontSize = 20.sp, color = Color.Yellow)
        Text(text = "Item 5", fontSize = 20.sp, color = Color.Gray)
        Text(text = "Item 6", fontSize = 20.sp, color = Color.Magenta)
    }
    Row {
        Text(text = "Item 7", fontSize = 20.sp, color = Color.Black)
        Text(text = "Item 8", fontSize = 20.sp, color = Color.Yellow)
        Text(text = "Item 9", fontSize = 20.sp, color = Color.Cyan)
    }
}


//@Preview(showBackground = true)
@Composable
fun PreviewColumnRowExample() {
    MyFirstJetpackComposeTheme {
        LazyColumnExample()
    }
}

@Preview(showBackground = true)
@Composable
fun App() {
    LazyColumnExample()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        color = Color.Black,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(color = Color.Magenta)
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstJetpackComposeTheme {
        Greeting("Andreu")
    }
}

////////////////////////////////////////////
//////////// PREVIEW LAZYCOLUMN EXAMPLE //////////
////////////////////////////////////////////
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LazyColumnExample() {
    var show by rememberSaveable {
        mutableStateOf(false)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.pmdm),
                fontSize = 32.sp,
                color = Color.Blue,
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = stringResource(id = R.string.school),
                fontSize = 24.sp,
                color = Color.White,
            )
        }
        item {
            Image(painter = painterResource(id = R.drawable.coliseo),
                contentDescription = "Imaguen del coliseo romano",
                modifier = Modifier
                    .padding(15.dp)
                    .height(150.dp)
                    .clickable { })
        }
        item {
            SimplesTextField("hola","Adios")
        }
        item {
            var t = Toast.makeText(LocalContext.current, "Dejame en paz", Toast.LENGTH_SHORT)
            var text by remember { mutableStateOf(TextFieldValue(text = "")) }
            ElevatedButton(onClick = { t.show() }, content = {Text( "Puto Boton")})
        }
        item{
            FloatingActionButton(onClick = {}, containerColor = colorResource(id = R.color.teal_700),  ) {
                Text(fontSize = 24.sp, text ="+")
            }
        }
        item {
            Button(onClick = { show = true }) {
                Text(text = "Dialog")
            }
        }
    }
    dialog(
        show,
        {show = false},
        {
            show = false
            Log.i("PMDM", "Hola que tal")
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimplesTextField(label: String, ph: String) {
    var text by remember { mutableStateOf(TextFieldValue(text = "")) }
    OutlinedTextField(
        value = text,
        onValueChange = {newtext -> text = newtext},
        label = {Text(text = label)},
        placeholder = {Text(ph)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "email")},
        trailingIcon = {Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "email")}
    )
}


@Composable
fun dialog(show: Boolean, onDissmiss: () -> Unit, onConfirm: () -> Unit): Unit {
    if (show){
        AlertDialog(
            onDismissRequest = { onDissmiss() },
            confirmButton = {
                TextButton(onClick = {onConfirm()}) {
                    Text(text = "Accept")
                }
            },
            dismissButton = {
                TextButton(onClick = {onDissmiss()}) {
                    Text(text = "Dismiss")
                }
            },
            title = { Text(text = "Hola")},
            text = { Text("Hola, ¿qué tal?") },
            icon = { Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "email")}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(): Unit {
    Scaffold (
        topBar = {/*BasicTopBar()*/AdvanceTopBar()},
        content = {padding -> BasicContent(padding)},
        floatingActionButton = {BasicFAB()},
        bottomBar = {BasicBottonBar()}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopBar(): Unit {
    TopAppBar(title = { Text(
        text = "PMDM Forum",
        color = Color.White)},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary))
}
@Composable
fun BasicContent(padding: PaddingValues): Unit {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ){
        Text(text = "Entry 1", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
        Text(text = "Entry 2", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
        Text(text = "Entry 3", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
    }
}
@Composable
fun BasicFAB(): Unit {
    FloatingActionButton(onClick = { /*TODO*/ }, containerColor = MaterialTheme.colorScheme.primary) {
        Text(text = "+", fontSize = 24.sp)
    }
}
@Composable
fun BasicBottonBar(): Unit {
    BottomAppBar(
        content = {
            Text(text = "HOME", fontSize = 20.sp)
        },
        containerColor = MaterialTheme.colorScheme.primary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvanceTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                },
                onClick = {},


                )
        },
        title = {
            Text(
                text = "PMDM Forum", color = Color.Cyan
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

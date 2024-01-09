package com.dam.pmdm.navegationdrawer

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.pmdm.navegationdrawer.ui.theme.NavegationDrawerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegationDrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UIWithModalNavDrawer()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIWithModalNavDrawer(): Unit {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheet(
            scope = scope,
            drawerState = drawerState
            )},
        content = {DrawerContent(
            scope = scope,
            drawerState = drawerState
        )}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet(scope: CoroutineScope, drawerState: DrawerState): Unit {
    val item = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.AccountCircle, Icons.Default.Close)
    val names = listOf("Home", "Search","Profile","Close")
    val selectedItem = remember {
        mutableStateOf(item[0])
    }
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(12.dp))
        item.forEachIndexed{index, item ->
            NavigationDrawerItem(
                icon = { Icon(imageVector = item, contentDescription = names[index]) },
                label = { Text(text = names[index]) },
                selected = item == selectedItem.value,
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedItem.value = item
                }

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(scope: CoroutineScope, drawerState: DrawerState): Unit {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(
                    "2 DAM - IESME",
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.holo_blue_dark),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )},
                navigationIcon = {
                    IconButton(onClick = {scope.launch { drawerState.open() }}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu buttons"
                        )
                    }
                },
            )
        },
        content = {padding -> LazyColumnExample(padding = padding)},
        //bottomBar = {BottomNavigationBarWithOnlyIcons()},
        //floatingActionButton = {AdvancedFAB()}
    )
}

@Composable
fun LazyColumnExample(padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(50) { index ->
            Text(
                text = "Item $index",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { /* Handle item click */ }
            )
        }
    }
}


package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.utils.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.utils.TextSummary

@Composable
@Preview
fun PreviewProfileScr(): Unit {
    val navController = rememberNavController()
    ProfileScr(navController = navController)
}
@Composable
fun ProfileScr(navController: NavController): Unit {
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.shopping_cart),
        content = { ProfileBodyContent() }
    )
}

@Composable
fun ProfileBodyContent(): Unit {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 70.dp)
            .fillMaxSize()
    ){
        item {
            Text(
                text = stringResource(id = R.string.your_data),
                fontSize = 40.sp,
            )
        }
        item {
            TextSummary(
                text = stringResource(id = R.string.your_data),
                icon = Icons.Default.Person,
                iconReference = stringResource(id = R.string.icon_person)
            )
        }
        item {
            Spacer(modifier = Modifier.width(10.dp))
            TextSummary(
                text = stringResource(id = R.string.your_data),
                icon = Icons.Default.Email,
                iconReference = stringResource(id = R.string.icon_email)
            )
        }
        item {
            Spacer(modifier = Modifier.width(10.dp))
            TextSummary(
                text = stringResource(id = R.string.your_data),
                icon = Icons.Default.LocationOn,
                iconReference = stringResource(id = R.string.icon_location)
            )
        }
        item {
            Spacer(modifier = Modifier.width(10.dp))
            TextSummary(
                text = stringResource(id = R.string.your_data),
                icon = Icons.Default.Lock,
                iconReference = stringResource(id = R.string.icon_password)
            )
        }
        item {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomEnd),
            ) {
                Icon(
                    painter = painterResource(R.drawable.aaa),
                    contentDescription = null,
                    tint = Color.Blue,
            )
            }
        }
        item{
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomEnd),
            ) {
                Image(
                    painter = painterResource(R.drawable.instragram_logo),
                    contentDescription = null
                )
            }
        }
        item{
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomEnd),
            ) {
                Image(
                    painter = painterResource(R.drawable.facebook_logo),
                    contentDescription = null
                )
            }
        }
    }
}
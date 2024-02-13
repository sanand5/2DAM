package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.tools.InfoTextfield
import com.dam.pmdm.solterraapp.ui.tools.NavigationUI
import com.dam.pmdm.solterraapp.ui.tools.NormalButton
import com.dam.pmdm.solterraapp.ui.viewmodel.UserViewModel
import com.dam.pmdm.solterraapp.ui.viewmodel.email
import com.dam.pmdm.solterraapp.ui.viewmodel.password
import com.dam.pmdm.solterraapp.ui.viewmodel.user

@Composable
@Preview
fun PreviewProfileScr() {
    val navController = rememberNavController()
    ProfileScr(navController = navController)
}

@Composable
fun ProfileScr(navController: NavController) {
    NavigationUI(
        navController = navController,
        text = stringResource(id = R.string.scr_Profile),
        content = { ProfileBodyContent() }
    )
}

@Composable
fun ProfileBodyContent() {
    val userViewModel = UserViewModel()
    val loc = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.edificioempresajpg),
            contentDescription = stringResource(id = R.string.img_edificioempresa),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 70.dp)
                .fillMaxSize()
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.txt_yourData),
                    fontSize = 40.sp,
                )
            }
            item {
                InfoTextfield(
                    value = user,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.icon_person),
                            tint = colorResource(id = R.color.solterraRed)
                        )
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.width(10.dp))
                InfoTextfield(
                    value = email,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = stringResource(id = R.string.icon_email),
                            tint = colorResource(id = R.color.solterraRed)
                        )
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.width(10.dp))
                var isTextVisible by remember { mutableStateOf(false) }
                InfoTextfield(
                    value = password,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = stringResource(id = R.string.icon_password),
                            tint = colorResource(id = R.color.solterraRed)
                        )
                    },
                    visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = if (isTextVisible) painterResource(id = R.drawable.eyeinvisible) else painterResource(
                                id = R.drawable.eye
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    isTextVisible = !isTextVisible
                                }
                                .width(25.dp),
                            tint = colorResource(id = R.color.solterraRed)
                        )
                    }

                )
            }
            item {
                val uriHandler = LocalUriHandler.current
                val linkUrl = stringResource(id = R.string.lnk_solterraInstagram)

                Image(
                    modifier = Modifier
                        .padding(14.dp)
                        .clickable {
                            uriHandler.openUri(linkUrl)
                        },
                    painter = painterResource(id = R.drawable.anuncioinstajpg),
                    contentDescription = stringResource(id = R.string.img_instagram)
                )
            }
            item {
                Spacer(modifier = Modifier.height(35.dp))
            }
            item {
                NormalButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.BottomCenter),
                    onClick = {
                        userViewModel.sendPasswordResetEmail(email, loc)
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_forgotpassword))
                }
            }
        }
    }
}
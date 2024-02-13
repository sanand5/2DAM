package com.dam.pmdm.solterraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.tools.*
import com.dam.pmdm.solterraapp.ui.viewmodel.UserViewModel
import com.dam.pmdm.solterraapp.ui.viewmodel.UserViewModel.*
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel
import com.dam.pmdm.solterraapp.ui.viewmodel.email
import com.dam.pmdm.solterraapp.ui.viewmodel.password
import com.dam.pmdm.solterraapp.ui.viewmodel.user
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogRegScr(
    navController: NavController
) {
    val viewModel = ViewModel()
    NavigationUI(
        navController = navController,
        text = stringResource(id = R.string.scr_LogReg),
        content = { LogRegBodyContent(viewModel, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LogRegBodyContent(viewModel: ViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    val emailLiveData: LiveData<String> = viewModel.email
    val passLiveData: LiveData<String> = viewModel.password
    val coroutineScope = rememberCoroutineScope()
    val loc = LocalContext.current
    val firebase = UserViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.casa),
            contentDescription = stringResource(id = R.string.img_casa),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(65.dp))
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_slogan),
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    color = colorResource(id = R.color.solterraRedOscuro)
                )
            }
            item {
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(
                            stringResource(id = R.string.txtf_user)
                        )
                    },
                    placeholder = { Text(text = stringResource(id = R.string.txtf_ph_name)) },
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .width(280.dp),
                    singleLine = true,
                    maxLines = 1,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.icon_person),
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        cursorColor = colorResource(id = R.color.solterraRed),
                        selectionColors = TextSelectionColors(
                            handleColor = colorResource(id = R.color.solterraRed),
                            backgroundColor = colorResource(id = R.color.solterraRed).copy(alpha = 0.5f)
                        ),
                        focusedIndicatorColor = colorResource(id = R.color.solterraRed),
                        unfocusedIndicatorColor = colorResource(id = R.color.solterraRed),
                        disabledIndicatorColor = colorResource(id = R.color.solterraRed),
                        focusedLabelColor = colorResource(id = R.color.solterraRed),
                    )
                )
            }
            item {
                EmailTextField(emailLiveData) {
                    viewModel.onLoginChange(
                        it,
                        viewModel.password.value.toString()
                    )
                }
            }
            item {
                PasswordTextField(passwordLiveData = passLiveData, onTextFieldChange = {
                    viewModel.onLoginChange(
                        viewModel.email.value.toString(),
                        it
                    )
                })
            }
            item { Spacer(modifier = Modifier.height(55.dp)) }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    NormalButton(
                        onClick = {
                            keyboardController?.hide()
                            if (name.isNotBlank() && emailLiveData.value.toString()
                                    .isNotBlank() && passLiveData.value.toString().isNotBlank()
                            ) {
                                coroutineScope.launch {
                                    if (firebase.signInWithFireBase(
                                            viewModel.email.value.toString(),
                                            viewModel.password.value.toString(),
                                            loc
                                        )
                                    ) {
                                        itemSelected = 1
                                        isNavigationEnabled = true
                                        user = name
                                        email = emailLiveData.value.toString()
                                        password = passLiveData.value.toString()
                                        navController.navigate(route = AppScreen.ShoppingCartScr.route)
                                    }
                                }
                            } else {
                                showToast(loc.getString(R.string.tst_e_fieldsEmpty), loc)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.btn_login))
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    InvestedButton(
                        onClick = {
                            keyboardController?.hide()
                            if (name.isNotBlank() || emailLiveData.value.toString()
                                    .isNotBlank() || passLiveData.value.toString().isNotBlank()
                            ) {
                                firebase.signUp(
                                    emailLiveData.value.toString(),
                                    passLiveData.value.toString(),
                                    loc
                                )
                            } else {
                                showToast(loc.getString(R.string.tst_e_fieldsEmpty), loc)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.btn_register))
                    }
                }
            }
        }
    }
}


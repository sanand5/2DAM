package com.dam.pmdm.activity_10.ui.app.ui.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dam.pmdm.activity_10.R
import com.dam.pmdm.activity_10.ui.theme.naranjaClaro
import com.dam.pmdm.activity_10.ui.theme.naranjaOscuro
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay

@Composable
fun InvestedButton(textReference: Int, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Red,
        ),
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Icon(Icons.Filled.ExitToApp, contentDescription = stringResource(id = R.string.exit_txt))
        Spacer(Modifier.width(10.dp))
        Text(
            text = stringResource(id = textReference),
        )
    }
}

@Composable
fun NormalButton(
    textReference: Int,
    modifier: Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
        ),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = textReference),
        )
    }
}

@Composable
fun TextSummary(text: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.iconFood),
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp),
            color = Color.Black
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldGeneral(
    textReference: Int,
    navController: NavController,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = {
            if (snackbarHostState != null) {
                SnackbarHost(hostState = snackbarHostState)
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = naranjaClaro),
                modifier = Modifier.clickable { navController.popBackStack() },
                title = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(id = R.string.KeyboardArrowLeft),
                        )
                        Text(text = stringResource(id = textReference))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )
        }
    ) {
        FondoAplicacion()
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(emailLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val email by emailLiveData.observeAsState("")
    TextField(
        modifier = Modifier.padding(
            horizontal = 20.dp
        ),
        value = email,
        onValueChange = { onTextFieldChange(it) },
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            Icon(
                Icons.Default.Email,
                contentDescription = stringResource(id = R.string.emailIcon)
            )
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = naranjaClaro,
            cursorColor = naranjaOscuro,
            focusedIndicatorColor = naranjaOscuro,
            focusedLabelColor = naranjaOscuro,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(passwordLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val password by passwordLiveData.observeAsState("")
    TextField(
        modifier = Modifier.padding(
            horizontal = 20.dp
        ),
        value = password,
        onValueChange = { onTextFieldChange(it) },
        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            Icon(
                Icons.Default.Person,
                contentDescription = stringResource(id = R.string.personIcon)
            )
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = naranjaClaro,
            cursorColor = naranjaOscuro,
            focusedIndicatorColor = naranjaOscuro,
            focusedLabelColor = naranjaOscuro,
        )
    )
}

@Composable
fun ForgotPassword() {
    Text(
        text = stringResource(id = R.string.forgot_password),
        modifier = Modifier.clickable {

        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.End
    )
}
@Composable
fun Register(email: String, password: String, loc: Context) {
    Text(
        text = stringResource(id = R.string.register_now),
        modifier = Modifier.clickable {
            signUp(email,password,loc)
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        textAlign = TextAlign.Start
    )
}

private fun signUp(username:String,password:String, loc: Context){
    Firebase.auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            showToast("Cuenta creada para usuario: $username.",loc)
        }else{
            showToast("Error en la creacion de cuenta",loc)
        }
    }
}

fun signInWithFireBase(username:String,password:String,loc:Context): Boolean {
    var ok = false
    Firebase.auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val user = Firebase.auth.currentUser
            if (user != null && user.isEmailVerified) {
                showToast(
                    "Login exitoso para usuario: $username. Cuenta verificada por correo",
                    loc
                )
            } else {
                showToast(
                    "Login exitoso para usuario: $username. Cuenta no verificada por correo",
                    loc
                )
                sendEmailVerification(loc)
            }
            ok = true
        } else {
            showToast("Error en la autntificacion", loc)
        }
    }
    return ok
}

fun sendEmailVerification(loc: Context) {
    val user = Firebase.auth.currentUser

    user?.sendEmailVerification()?.addOnCompleteListener{task->
        if(task.isSuccessful){
            showToast("Se ha enviado un correo de verificacion a tu correo electronico",loc)
        }else{
            showToast("Error al enviar el correo de verificacion"+task.exception?.message,loc)
        }
    }
}

fun showToast(mensaje: String,loc: Context) {
    Toast.makeText(loc,mensaje, Toast.LENGTH_SHORT).show()
}

@Composable
fun FondoAplicacion() {
    val imagenFondo = painterResource(id = R.drawable.background)
    Image(
        painter = imagenFondo,
        contentDescription = stringResource(id = R.string.wallpaper),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

class ViewModel : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData("")
    private val _password: MutableLiveData<String> = MutableLiveData("")
    private val _loginEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val loginEnable: LiveData<Boolean> = _loginEnable

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    suspend fun onLoginPressed() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean {
        return password.length > 8
    }

}
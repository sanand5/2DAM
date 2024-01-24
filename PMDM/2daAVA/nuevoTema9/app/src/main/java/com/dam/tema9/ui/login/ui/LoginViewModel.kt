package com.dam.tema9.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val _email : MutableLiveData<String> = MutableLiveData("")
    private val _password : MutableLiveData<String> = MutableLiveData("")
    private val _loginEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val loginEnable: LiveData<Boolean> = _loginEnable
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value  = isValidEmail(email) && isValidPassword(password)
    }

    suspend fun onLoginPressed() {
        _isLoading.value = true;
        delay(4000)
        _isLoading.value = false;
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 5
}
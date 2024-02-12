package com.dam.pmdm.solterraapp.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

var user: String = ""
var email: String = ""
var password: String = ""

class ViewModel : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData("")
    private val _password: MutableLiveData<String> = MutableLiveData("")
    private val _loginEnable: MutableLiveData<Boolean> = MutableLiveData(false)

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

}
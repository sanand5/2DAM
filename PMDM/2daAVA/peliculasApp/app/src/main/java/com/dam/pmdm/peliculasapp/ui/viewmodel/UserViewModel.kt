package com.dam.pmdm.peliculasapp.ui.viewmodel

import android.content.Context
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class UserViewModel {
    public fun signInWithFirebase(username: String, password:String, context: Context, onLoginSuccess: (FirebaseUser) -> Unit) {
        if (username.isEmpty() || password.isEmpty()) {
            showToast("Datos vacios.",context)
        } else {
            Firebase.auth.signInWithEmailAndPassword(username,password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        showToast("Login exitoso para usuario:  $username. Cuenta verificada por correo",context )
                        onLoginSuccess(user)
                    } else {
                        showToast("Login exitoso para usuario $username. Cuenta no verificada por correo.",context)
                        sendEmailVerification(context)
                    }
                } else {
                    showToast("Error en la autenticación: ${task.exception?.message}",context)
                }
            }
        }
    }

    private fun showToast(message: String, loc: Context){
        Toast.makeText(loc, message, Toast.LENGTH_LONG).show()
    }

    private fun sendEmailVerification(context: Context) {
        val user = Firebase.auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Se ha enviado un correo de verificación a tu dirección de correo electrónico", context)
            } else {
                showToast("Error al enviar el correo de verificación: ${task.exception?.message}", context)
            }
        }
    }

    fun signUp(username: String, password: String, loc: Context) {
        if (username.isEmpty() || password.isEmpty()) {
            showToast("Datos vacios ", loc)
        } else {
            Firebase.auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Cuenta creada para usuario: $username.", loc)
                    } else {
                        showToast("Error en la creacion de cuenta", loc)
                    }
                }
        }
    }


}
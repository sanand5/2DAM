package com.dam.pmdm.solterraapp.ui.viewmodel

import android.content.Context
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.tools.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

class UserViewModel {
    private fun sendEmailVerification(loc: Context) {
        val user = Firebase.auth.currentUser

        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(loc.getString(R.string.tst_i_verificationEmailSent), loc)
            } else {
                showToast(loc.getString(R.string.tst_e_SendingEmail) + task.exception?.message, loc)
            }
        }
    }

    suspend fun signInWithFireBase(username: String, password: String, loc: Context): Boolean {
        val completableFuture = CompletableFuture<Boolean>()
        Firebase.auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                completableFuture.complete(true)
                val user = Firebase.auth.currentUser
                if (!(user != null && user.isEmailVerified)) {
                    showToast(loc.getString(R.string.tst_o_loginSuccessMessage, username), loc)
                    sendEmailVerification(loc)
                }
            } else {
                completableFuture.complete(false)
                showToast(loc.getString(R.string.tst_e_authenticationFailed), loc)
            }
        }
        return completableFuture.await()
    }

    fun sendPasswordResetEmail(email: String?, loc: Context) {
        if (!email.isNullOrBlank()) {
            Firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(loc.getString(R.string.tst_i_passwordReset), loc)
                } else {
                    showToast(
                        loc.getString(R.string.tst_e_SendingPassword) + "${task.exception?.message}",
                        loc
                    )
                }
            }
        } else {
            showToast(loc.getString(R.string.tst_e_emailNull), loc)
        }
    }

    fun signUp(username: String, password: String, loc: Context) {
        if (username.isNotBlank() && password.isNotBlank()) {
            Firebase.auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast(loc.getString(R.string.tst_o_accountCreated) + " $username.", loc)
                    } else {
                        showToast(loc.getString(R.string.tst_e_Account), loc)
                    }
                }
        } else {
            showToast(loc.getString(R.string.tst_e_fieldEmpty), loc)
        }

    }
}

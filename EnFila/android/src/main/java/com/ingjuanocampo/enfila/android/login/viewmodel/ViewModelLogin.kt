package com.ingjuanocampo.enfila.android.login.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.ingjuanocampo.enfila.android.utils.launchGeneral
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ViewModelLogin : ViewModel() {

    var verificationCode: String? = null
    private var verificationId: String? = null
    val state = MutableLiveData<LoginState>()
    var activity: Activity? = null

    var phoneNumber: String = ""
        set(value) {
            if (value.isNotEmpty() && value.count() == 10) {
                field = value
                state.value = LoginState.NumberSet
            }
        }


    private val auth by lazy { FirebaseAuth.getInstance() }

    private val authCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            state.value = LoginState.AuthError(p0)
        }

        override fun onCodeSent(_verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(_verificationId, token)
            verificationId = _verificationId
            state.value = LoginState.ToVerifyCode
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    // Storage user
                    state.value = LoginState.Authenticated
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    state.value = LoginState.AuthError(task.exception?: Exception("No login"))
                    // Update UI
                }
            }
    }

    fun doLogin(activity: Activity) {
        this.activity = activity
        viewModelScope.launch {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+57$phoneNumber")       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // Activity (for callback binding)
                .setCallbacks(authCallbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

        }
    }

    fun verify() {
        viewModelScope.launchGeneral {
            val credential = PhoneAuthProvider.getCredential(verificationId!!, verificationCode)
            signInWithPhoneAuthCredential(credential)


        }
    }

}
package com.ingjuanocampo.enfila.android.login.viewmodel

import java.lang.Exception

sealed class LoginState {
    object NumberSet: LoginState()
    object ToVerifyCode: LoginState()
    object Authenticated: LoginState()
    object NewAccount: LoginState()
    data class AuthError(val e: Exception): LoginState()
}
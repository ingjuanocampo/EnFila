package com.ingjuanocampo.enfila.domain.usecases.signing

sealed class AuthState {
    object Authenticated: AuthState()
    object NewAccount: AuthState()
    data class AuthError(val e: Throwable): AuthState()
}
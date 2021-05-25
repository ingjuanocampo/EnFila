package com.ingjuanocampo.enfila.domain.state

import com.ingjuanocampo.enfila.domain.state.states.LoggedState
import com.ingjuanocampo.enfila.domain.state.states.NotLoggedState

class AppStateProvider(
    private val loggedState: LoggedState,
    private val notLoggedState: NotLoggedState
) {

    private fun isLogged() = false

    private var currentState: AppState = if (isLogged()) loggedState else notLoggedState

    fun provideCurrentState(): AppState {
        return currentState
    }

    fun toNotLoggedState() {
        currentState = notLoggedState
    }

    fun toLoggedState() {
        currentState = loggedState
    }


}
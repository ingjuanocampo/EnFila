package com.ingjuanocampo.enfila.android.state

import android.content.Context
import android.content.Intent
import com.ingjuanocampo.enfila.android.ActivityLoggedUser
import com.ingjuanocampo.enfila.domain.state.states.LoggedState

class LoggedStateImpl(private val context: Context): LoggedState {

    override fun navigateLaunchScreen() {
        context.startActivity(Intent(context, ActivityLoggedUser::class.java))
    }

}
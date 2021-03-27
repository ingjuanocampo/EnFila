package com.ingjuanocampo.enfila.android.state

import android.content.Context
import android.content.Intent
import com.ingjuanocampo.enfila.android.ActivityLogin
import com.ingjuanocampo.enfila.domain.state.states.NotLoggedState

class NotLoggedImpl(private val context: Context): NotLoggedState {

    override fun navigateLaunchScreen() {
        context.startActivity(Intent(context, ActivityLogin::class.java))
    }
}
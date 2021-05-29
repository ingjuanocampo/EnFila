package com.ingjuanocampo.enfila.android.lobby.profile.viewmodel

sealed class ProfileState {
    data class CreationFlow(val phone: String): ProfileState()
}
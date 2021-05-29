package com.ingjuanocampo.enfila.android.lobby.profile.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingjuanocampo.enfila.android.utils.launchGeneral

class ViewModelFragmentProfile : ViewModel() {

    val state = MutableLiveData<ProfileState>()

    fun init(arguments: Bundle?) {
        launchGeneral {
            var phone = arguments?.getString("phone")
            if (phone != null ) {
                state.postValue(ProfileState.CreationFlow(phone))
            } else {
                // Load info internally
            }
        }
    }




}
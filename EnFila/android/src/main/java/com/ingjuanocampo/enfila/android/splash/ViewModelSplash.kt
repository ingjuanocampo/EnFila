package com.ingjuanocampo.enfila.android.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ViewModelSplash : ViewModel() {

    val state =  MutableLiveData<SplashState>()

    fun launchSplash() {
        viewModelScope.launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            state.postValue(Navigate)
        }
    }

}
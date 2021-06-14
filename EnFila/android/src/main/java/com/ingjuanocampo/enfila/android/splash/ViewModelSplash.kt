package com.ingjuanocampo.enfila.android.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingjuanocampo.enfila.domain.di.domain.DomainModule.provideLoadInitialInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ViewModelSplash : ViewModel() {

    val state =  MutableLiveData<SplashState>()

    val loadInitInfoUC = provideLoadInitialInfo()

    fun launchSplash() {
        viewModelScope.launch {
            loadInitInfoUC()
            delay(TimeUnit.SECONDS.toMillis(1))
            state.postValue(Navigate)
        }
    }

}
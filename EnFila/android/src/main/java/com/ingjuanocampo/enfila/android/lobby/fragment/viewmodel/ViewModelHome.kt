package com.ingjuanocampo.enfila.android.lobby.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingjuanocampo.enfila.domain.di.domain.DomainModule
import com.ingjuanocampo.enfila.domain.model.Shift
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelHome : ViewModel() {

    private val companySiteInteractions = DomainModule.providesCompanySiteInteractions()
    val state = MutableLiveData<HomeState>()

    fun loadCurrentTurn() {
        viewModelScope.launch(Dispatchers.Default) {
            state.postValue(HomeState.Loading)

            companySiteInteractions.load().collect {
                loadAndPostCurrent()
            }

        }
    }

    private fun loadAndPostCurrent() {
        val currentTurn = companySiteInteractions.getCurrentTurn()
        if (currentTurn!= null) {
            state.postValue(HomeState.CurrentTurn(currentTurn))
        } else {
            state.postValue(HomeState.Empty)
        }
    }

    fun next() {
        viewModelScope.launch(Dispatchers.Default) {
            companySiteInteractions.next()
            loadAndPostCurrent()
        }
    }

}

sealed class HomeState {
    object Loading: HomeState()
    object Empty: HomeState()
    data class CurrentTurn(val shift: Shift) : HomeState()
}
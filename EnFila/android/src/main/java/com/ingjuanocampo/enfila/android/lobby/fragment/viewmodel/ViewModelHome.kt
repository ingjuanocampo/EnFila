package com.ingjuanocampo.enfila.android.lobby.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingjuanocampo.enfila.android.utils.launchGeneral
import com.ingjuanocampo.enfila.domain.di.domain.DomainModule
import com.ingjuanocampo.enfila.domain.model.Shift
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelHome : ViewModel() {

    private val companySiteInteractions = DomainModule.providesCompanySiteInteractions()
    val state = MutableLiveData<HomeState>()

    fun loadCurrentTurn() {
        viewModelScope.launchGeneral {
            state.postValue(HomeState.Loading)

            companySiteInteractions.load().collect {
                loadAndPostCurrent()
            }

        }
    }

    private fun loadAndPostCurrent() {
        val currentTurn = companySiteInteractions.getCurrentTurn()
        updateCurrentTurn(currentTurn)
    }

    private fun updateCurrentTurn(currentTurn: Shift?) {
        if (currentTurn!= null) {
            state.postValue(HomeState.CurrentTurn(currentTurn))
        } else {
            state.postValue(HomeState.Empty)
        }
    }

    fun next() {
        viewModelScope.launchGeneral {
            updateCurrentTurn(companySiteInteractions.next())
        }
    }

}

sealed class HomeState {
    object Loading: HomeState()
    object Empty: HomeState()
    data class CurrentTurn(val shift: Shift) : HomeState()
}
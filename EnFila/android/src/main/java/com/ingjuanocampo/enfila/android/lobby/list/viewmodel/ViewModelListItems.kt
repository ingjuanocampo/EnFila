package com.ingjuanocampo.enfila.android.lobby.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingjuanocampo.enfila.android.lobby.list.ShiftItem
import com.ingjuanocampo.enfila.android.utils.launchGeneral
import com.ingjuanocampo.enfila.domain.di.domain.DomainModule

class ViewModelListItems : ViewModel() {

    val state = MutableLiveData<List<ShiftItem>>()
    private val companySiteInteractions = DomainModule.providesCompanySiteInteractions()

    fun load() {
        viewModelScope.launchGeneral {
            state.value = companySiteInteractions.getActiveShifts()?.map {

                ShiftItem(id = it.id?.toInt() ?: 0,
            name = it.contactId ?: "",
            phone = ""  ,
            currentTurn = it.number.toString(),
                    issueDate = it.date ?: 0L
            ) }
        }
    }

}
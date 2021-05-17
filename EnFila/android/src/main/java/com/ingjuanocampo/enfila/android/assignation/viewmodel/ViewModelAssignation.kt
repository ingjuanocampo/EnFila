package com.ingjuanocampo.enfila.android.assignation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingjuanocampo.enfila.android.utils.launchGeneral
import com.ingjuanocampo.enfila.domain.di.domain.DomainModule

class ViewModelAssignation : ViewModel() {

    var phoneNumber: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                assignationState.value = AssignationState.NumberSet
            }
        }
    var name: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                assignationState.value = AssignationState.NameAndNoteSet
            }
        }
    var note: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                assignationState.value = AssignationState.NameAndNoteSet
            }
        }
    var tunr: Int = 0


    var closestTurn = 0

    private val shiftInteractions = DomainModule.providesShiftInteractions()
    val assignationState: MutableLiveData<AssignationState> = MutableLiveData(AssignationState.IDLE)


    init {
        calculateNextTurn()
    }

    private fun calculateNextTurn() {
        viewModelScope.launchGeneral {
            closestTurn = shiftInteractions.getClosestShiftTurn() + 1
            tunr = closestTurn
        }
    }

    fun setTurn(turn: Int) {
        if (tunr > this.closestTurn) {
            assignationState.value = AssignationState.ErrorTurnAssigned
        } else {
            this.tunr = tunr
        }
    }

    fun createAssignation() {

    }
}
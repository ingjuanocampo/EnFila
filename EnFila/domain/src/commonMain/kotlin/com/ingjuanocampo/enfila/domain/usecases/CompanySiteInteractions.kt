package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.model.Shift

interface CompanySiteInteractions {

    fun next(): Shift

    fun add(shift: Shift)

    fun getActiveShifts(): List<Shift>

    fun getHistory(): List<Shift>

    fun getCurrentTurn(): Shift?

}
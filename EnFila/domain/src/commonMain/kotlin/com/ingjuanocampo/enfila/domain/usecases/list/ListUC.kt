package com.ingjuanocampo.enfila.domain.usecases.list

import com.ingjuanocampo.enfila.domain.usecases.ShiftInteractions
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import kotlinx.coroutines.flow.map

class ListUC(
    private val shiftRepository: ShiftRepository,
    private val shiftInteractions: ShiftInteractions
) {

    fun loadShift() = shiftRepository
        .getAllObserveData().map { shifts ->
            shifts.map {
                shiftInteractions.loadShiftWithClient(it)
            }
        }


}
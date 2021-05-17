package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Client
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.entity.getNow
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import kotlinx.coroutines.flow.firstOrNull

class ShiftInteractions(val shiftRepository: ShiftRepository
                        , val clientRepository: Repository<List<Client>>) {


    suspend fun next(current: Shift?): ShiftWithClient? {

        current?.let { updateShift(it.apply {
            endDate = getNow()
        }, ShiftState.FINISHED) }
        val closestShift = shiftRepository.getClosestShift().firstOrNull()
        closestShift?.state = ShiftState.CALLING

        closestShift?.let {
            shiftRepository.createOrUpdate(listOf(it))
        }
        return closestShift?.let {
            ShiftWithClient(
                it,
                clientRepository.getById(it.contactId).first()
            )
        }
    }
    
    suspend fun getClosestShiftTurn(): Int {
        return shiftRepository.getClosestShift().firstOrNull()?.number ?: 1
    }

    suspend fun updateShift(shift: Shift, state: ShiftState) {
        shift.state = state
        shiftRepository.createOrUpdate(listOf(shift))
    }

    suspend fun loadShiftWithClient(shift: Shift): ShiftWithClient {
        val client = clientRepository.getById(shift.contactId).first()
        return ShiftWithClient(shift, client)
    }
}
package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Client
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import kotlinx.coroutines.flow.firstOrNull

class ShiftInteractions(val shiftRepository: ShiftRepository
                        , val clientRepository: Repository<List<Client>>) {


    suspend fun next(current: Shift): ShiftWithClient? {

        updateShift(current, ShiftState.FINISHED)
        val closestShift = shiftRepository.getClosestShift().firstOrNull()
        closestShift?.state = ShiftState.CALLING

        closestShift?.let {
            shiftRepository.createOrUpdate(listOf(it))
        }
        return closestShift?.let {
            ShiftWithClient(
                it,
                clientRepository.getById(it.contactId).first())
        }
    }

    suspend fun updateShift(shift: Shift, state: ShiftState) {
        shift.state = state
        shiftRepository.createOrUpdate(listOf(shift))
    }

    suspend fun loadShiftWithClient(shift: Shift): ShiftWithClient {
        val client = clientRepository.getById(shift.contactId).first()
        return ShiftWithClient(shift, client)
    }

   /*private var cachedShift: Shift? = null

    fun getAll() = shiftsRepo.getAllObserveData()

    fun loadShiftById(id: String) = shiftsRepo.getAndObserveData(GetShiftById(id))
        .map {
            cachedShift = it.firstOrNull()
            if (cachedShift == null) IllegalStateException("Shift not found")
            cachedShift
        }

    fun getCurrentShiftFlow() = shiftsRepo.getAndObserveData(GetCurrentShift)

    fun call() {
        cachedShift?.let {
            it.state = ShiftState.CALLING
            shiftsRepo.createOrUpdate(listOf(it))
        }
    }

    fun delete() {
        cachedShift?.let {
            shiftsRepo.delete(listOf(it))
        }
    }

    fun cancel() {
        cachedShift?.let {
            it.state = ShiftState.CANCELLED
            shiftsRepo.createOrUpdate(listOf(it))
        }
    }

    fun finish() {
        cachedShift?.let {
            it.state = ShiftState.FINISHED
            shiftsRepo.createOrUpdate(listOf(it))
        }
    }*/

}

class GetShiftById(val id: String) : RepoInfo
object GetCurrentShift: RepoInfo

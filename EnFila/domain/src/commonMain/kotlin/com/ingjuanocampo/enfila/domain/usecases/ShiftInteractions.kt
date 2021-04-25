package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository

class ShiftInteractions(private val shiftsRepo: Repository<List<Shift>>) {

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

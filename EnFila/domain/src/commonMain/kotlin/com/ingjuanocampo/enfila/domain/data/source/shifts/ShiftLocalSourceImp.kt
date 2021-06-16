package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.entity.Shift
import kotlinx.coroutines.flow.Flow

class ShiftLocalSourceImp: ShiftLocalSource {

    override fun getClosestShift(): Flow<Shift?> {
        TODO("Not yet implemented")
    }

    override fun getLastShift(): Flow<Shift?> {
        TODO("Not yet implemented")
    }

    override fun getCallingShift(): Flow<Shift?> {
        TODO("Not yet implemented")
    }

    override suspend fun createOrUpdate(data: List<Shift>) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(dataToDelete: List<Shift>) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllObserveData(): Flow<List<Shift>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllData(): List<Shift>? {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): List<Shift>? {
        TODO("Not yet implemented")
    }
}
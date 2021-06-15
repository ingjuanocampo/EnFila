package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.entity.Shift
import kotlinx.coroutines.flow.Flow

actual class ShiftsRemoteSourceFirebase {

    actual fun fetchData(id: String): Flow<List<Shift>?> {
        TODO()
    }
    actual fun updateData(data: List<Shift>): Flow<List<Shift>?> {
        TODO()
    }

    actual fun updateData(data: Shift): Flow<Shift?> {
        TODO("Not yet implemented")
    }
}
package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.Shift

class ShiftsRemoteSource: RemoteSource<List<Shift>> {

    override suspend fun fetchData(id: String): List<Shift> {
        return emptyList()
    }

    override suspend fun updateData(data: List<Shift>) {
    }


}
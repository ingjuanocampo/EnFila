package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Shift

class ShiftsRemoteSource: RemoteSource<List<Shift>> {


    override fun fetchData(): List<Shift> {
        return emptyList()
    }

    override fun updateData(data: List<Shift>) {
    }


}
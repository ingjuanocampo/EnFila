package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.CompanySite

class CompanySiteRemoteSource: RemoteSource<List<CompanySite>> {

    override suspend fun fetchData(id: String): List<CompanySite> {
        return emptyList()
    }

    override suspend fun createOrUpdate(data: List<CompanySite>) {
        TODO("Not yet implemented")
    }
}
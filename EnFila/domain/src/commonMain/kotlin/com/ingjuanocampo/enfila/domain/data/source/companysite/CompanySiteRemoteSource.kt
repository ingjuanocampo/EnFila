package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.CompanySite

class CompanySiteRemoteSource: RemoteSource<List<CompanySite>> {


    override fun fetchData(): List<CompanySite> {
        return emptyList()
    }

    override fun updateData(data: List<CompanySite>) {
        TODO("Not yet implemented")
    }
}
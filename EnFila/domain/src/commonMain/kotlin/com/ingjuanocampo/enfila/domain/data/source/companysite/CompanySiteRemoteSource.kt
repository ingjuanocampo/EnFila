package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.model.CompanySite

class CompanySiteRemoteSource: RemoteSource<List<CompanySite>> {

    override fun fetchData(repoInfo: RepoInfo?): List<CompanySite> {
        return emptyList()
    }

    override fun updateData(data: List<CompanySite>, repoInfo: RepoInfo?) {

    }
}
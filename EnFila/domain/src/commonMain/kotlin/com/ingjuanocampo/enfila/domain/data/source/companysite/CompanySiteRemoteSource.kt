package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class CompanySiteRemoteSource(private val companySiteLocalSource: CompanyInfoRemoteSource): RemoteSource<List<CompanySite>> {

    override fun fetchInfoFlow(id: String): Flow<List<CompanySite>?> {
        return companySiteLocalSource.fetchData(id)
    }

    override suspend fun createOrUpdate(data: List<CompanySite>) {
        companySiteLocalSource.updateData(data = data).firstOrNull()
    }

    override suspend fun fetchData(id: String): List<CompanySite>? {
        return fetchInfoFlow(id).firstOrNull()
    }

}

expect class CompanyInfoRemoteSource() {
    fun fetchData(id: String): Flow<List<CompanySite>?>
    fun updateData(data: List<CompanySite>): Flow<List<CompanySite>?>

}
package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val list = arrayListOf(CompanySite(
    id = "companyid",
    name = "Sanduches Cuba"
))

class CompanySiteMockSource: LocalSource<List<CompanySite>> {


    override fun createOrUpdate(data: List<CompanySite>) {
        list.addAll(data)
    }

    override fun getData(repoInfo: RepoInfo?): List<CompanySite> {
        return list
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<List<CompanySite>> {
        return flow { emit(list) }
    }

    override fun delete(dataToDelete: List<CompanySite>) {
        list.remove(dataToDelete)
    }

    override fun getAllObserveData(): Flow<List<CompanySite>> {
        return flow { emit(list) }
    }

    override fun getAllData(): List<CompanySite> {
        return list
    }

    override fun getById(id: String): List<CompanySite> {
        return list.filter { it.id == id }
    }
}
package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val companySiteList: ArrayList<CompanySite> = ArrayList()

class CompanySiteLocalSource: LocalSource<List<CompanySite>> {

    override suspend fun createOrUpdate(data: List<CompanySite>) {
        companySiteList.addAll(data)
    }

    override suspend fun delete(dataToDelete: List<CompanySite>) {
        companySiteList.remove(dataToDelete)
    }

    override fun getAllObserveData(): Flow<List<CompanySite>> {
        return flow { emit(companySiteList) }
    }

    override suspend fun getAllData(): List<CompanySite> {
        return companySiteList
    }

    override suspend fun getById(id: String): List<CompanySite> {
        return companySiteList.filter { it.id == id }
    }

    override suspend fun delete(id: String) {

    }

}
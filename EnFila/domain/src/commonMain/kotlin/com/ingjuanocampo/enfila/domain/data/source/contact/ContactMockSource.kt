package com.ingjuanocampo.enfila.domain.data.source.contact

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val list = arrayListOf(
    Client(
        id = "1",
        name = "Topacio",
        phone = "12135550993"
    ),
    Client(
        id = "2",
        name = "Julia",
        phone = "3135123550993"
    ),
    Client(
        id = "3",
        name = "Jose",
        phone = "31315550993"
    ),
    Client(
        id = "4",
        name = "Benito",
        phone = "3335550993"
    ),
    Client(
        id = "5",
        name = "Sofitronica",
        phone = "3135552133"
    )


)

class ContactMockSource : LocalSource<List<Client>> {
    override suspend fun createOrUpdate(data: List<Client>) {
        list.addAll(data)
    }

    override suspend fun getData(repoInfo: RepoInfo?): List<Client> {
        return list
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<List<Client>> {
        return flow { emit(list) }
    }

    override suspend fun delete(dataToDelete: List<Client>) {
        list.remove(dataToDelete)
    }

    override fun getAllObserveData(): Flow<List<Client>> {
        return flow { emit(list) }
    }

    override suspend fun getAllData(): List<Client> {
        return list
    }

    override suspend fun getById(id: String): List<Client> {
        return list.filter { it.id == id }
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }
}
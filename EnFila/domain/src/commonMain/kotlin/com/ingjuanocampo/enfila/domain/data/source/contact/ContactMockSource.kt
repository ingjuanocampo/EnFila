package com.ingjuanocampo.enfila.domain.data.source.contact

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val list = arrayListOf(
    Client(
        id = "3137550991",
        name = "Topacio",
    ),
    Client(
        id = "3137550992",
        name = "Julia",
    ),
    Client(
        id = "3137550993",
        name = "Jose",
    ),
    Client(
        id = "3137550994",
        name = "Benito",
    ),
    Client(
        id = "3137550995",
        name = "Sofitronica",
    )


)

class ContactMockSource : LocalSource<List<Client>> {
    override suspend fun createOrUpdate(data: List<Client>) {
        data.forEach { newClient ->
            if (list.firstOrNull { it.id == newClient.id } == null) {
                list.add(newClient)
            }
        }
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
package com.ingjuanocampo.enfila.domain.data.source.contact

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val list = arrayListOf(
    Contact(
        id = "1",
        name = "Topacio",
        phone = "12135550993"
    ),
    Contact(
        id = "2",
        name = "Julia",
        phone = "3135123550993"
    ),
    Contact(
        id = "3",
        name = "Jose",
        phone = "31315550993"
    ),
    Contact(
        id = "4",
        name = "Benito",
        phone = "3335550993"
    ),
    Contact(
        id = "5",
        name = "Sofitronica",
        phone = "3135552133"
    )


)

class ContactMockSource : LocalSource<List<Contact>> {
    override fun createOrUpdate(data: List<Contact>) {
        list.addAll(data)
    }

    override fun getData(repoInfo: RepoInfo?): List<Contact> {
        return list
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<List<Contact>> {
        return flow { emit(list) }
    }

    override fun delete(dataToDelete: List<Contact>) {
        list.remove(dataToDelete)
    }

    override fun getAllObserveData(): Flow<List<Contact>> {
        return flow { emit(list) }
    }

    override fun getAllData(): List<Contact> {
        return list
    }

    override fun getById(id: String): List<Contact> {
        return list.filter { it.id == id }
    }
}
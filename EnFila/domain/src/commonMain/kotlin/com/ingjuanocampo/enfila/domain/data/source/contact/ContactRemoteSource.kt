package com.ingjuanocampo.enfila.domain.data.source.contact

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.Contact

class ContactRemoteSource: RemoteSource<List<Contact>> {


    override fun fetchData(): List<Contact> {
        return emptyList()
    }

    override fun updateData(data: List<Contact>) {
    }
}
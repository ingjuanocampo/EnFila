package com.ingjuanocampo.enfila.domain.data.source.contact

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.Client

class ContactRemoteSource: RemoteSource<List<Client>> {


    override fun fetchData(id: String): List<Client> {
        return emptyList()
    }

    override fun updateData(data: List<Client>) {
    }
}
package com.ingjuanocampo.enfila.domain.data.source

interface RemoteSource<Data> {
    fun fetchData(id: String): Data
    fun updateData(data: Data)
}
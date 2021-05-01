package com.ingjuanocampo.enfila.domain.data.source

interface RemoteSource<Data> {
    fun fetchData(): Data
    fun updateData(data: Data)
}
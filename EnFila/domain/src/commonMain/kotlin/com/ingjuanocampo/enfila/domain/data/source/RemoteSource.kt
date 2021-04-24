package com.ingjuanocampo.enfila.domain.data.source

interface RemoteSource<Data> {
    fun fetchData(): Data
    fun fetchData(repoInfo: RepoInfo?): Data
    fun updateData(data: Data, repoInfo: RepoInfo? = null)
}
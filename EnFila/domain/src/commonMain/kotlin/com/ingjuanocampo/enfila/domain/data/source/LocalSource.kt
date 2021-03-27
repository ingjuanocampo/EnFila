package com.ingjuanocampo.enfila.domain.data.source

import kotlinx.coroutines.flow.Flow

interface LocalSource<Data> {
    fun createOrUpdate(data: Data)
    fun getData(repoInfo: RepoInfo?): Data
    fun getDataAndObserve(repoInfo: RepoInfo?): Flow<Data>
    fun delete(dataToDelete: Data)

}
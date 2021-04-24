package com.ingjuanocampo.enfila.domain.usecases.repository

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import kotlinx.coroutines.flow.Flow

interface Repository<Data> {

    fun createOrUpdate(data: Data, repoInfo: RepoInfo? = null)

    fun refresh(repoInfo: RepoInfo? = null): Data

    fun getAndObserveData(repoInfo: RepoInfo? = null): Flow<Data>

    fun getData(repoInfo: RepoInfo? = null): Data

    fun getAllObserveData(): Flow<Data>

    fun getAllData(): Data

    fun getById(id: String): Data

    fun delete(listOf: Data)

}


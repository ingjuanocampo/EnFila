package com.ingjuanocampo.enfila.domain.usecases.repository

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import kotlinx.coroutines.flow.Flow

interface Repository<Data> {

    fun createOrUpdate(data: Data, repoInfo: RepoInfo? = null)

    fun refresh(repoInfo: RepoInfo? = null): Data

    fun getAndObserveData(repoInfo: RepoInfo?): Flow<Data>

}


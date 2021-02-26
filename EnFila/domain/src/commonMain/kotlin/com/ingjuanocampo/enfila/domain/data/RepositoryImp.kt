package com.ingjuanocampo.enfila.domain.data

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.data.util.RateLimiter
import com.ingjuanocampo.enfila.domain.usecases.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

class RepositoryImp<Data>(
    private val remoteSource: RemoteSource<Data>,
    private val localSource: LocalSource<Data>
) : Repository<Data> {

    private  val keyId = Clock.System.now().epochSeconds.toString()
    private val rateLimiter = RateLimiter<String>(15)

    override fun createOrUpdate(data: Data, repoInfo: RepoInfo?) {
        remoteSource.updateData(data, repoInfo)
        localSource.createOrUpdate(data)
    }

    override fun refresh(repoInfo: RepoInfo?): Data {
        val data = remoteSource.fetchData(repoInfo)
        localSource.createOrUpdate(data)
        return data
    }

    override fun getAndObserveData(repoInfo: RepoInfo?): Flow<Data> {
        return flow {
            val initialData = localSource.getData(repoInfo)
            emit(initialData) // First value from Local
            if (shouldFetch(initialData)) {
                val dataToLocal = remoteSource.fetchData(repoInfo)
                localSource.createOrUpdate(dataToLocal)
            }
        }.flatMapConcat { localSource.getDataAndObserve(repoInfo) }
    }

    private fun shouldFetch(initialData: Data?): Boolean {
       return if (initialData != null) {
           rateLimiter.shouldFetch(key = keyId)
        } else true
    }

    override fun getData(repoInfo: RepoInfo?): Data {
        return localSource.getData(repoInfo)
    }

    override fun delete(dataToDelete: Data) {
        localSource.delete(dataToDelete)
    }
}
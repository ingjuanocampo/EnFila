package com.ingjuanocampo.enfila.domain.data

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.util.RateLimiter
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

open class RepositoryImp<Data>(
    private val remoteSource: RemoteSource<Data>,
    private val localSource: LocalSource<Data>
) : Repository<Data> {

    private val keyId = Clock.System.now().epochSeconds.toString()
    private val rateLimiter = RateLimiter<String>(15)

    override suspend fun createOrUpdate(data: Data) {
        remoteSource.updateData(data)
        localSource.createOrUpdate(data)
    }

    override suspend fun refresh(): Data? {
        val data = remoteSource.fetchData(id)
        data?.let { localSource.createOrUpdate(it) }
        return data
    }

    private fun shouldFetch(initialData: Data?): Boolean {
        return if (initialData != null) {
            rateLimiter.shouldFetch(key = keyId)
        } else true
    }

    override suspend fun delete(dataToDelete: Data) {
        localSource.delete(dataToDelete)
    }

    override fun getAllObserveData(): Flow<Data?> {
        return flow {
            val initialData = localSource.getAllData()
            if (initialData != null) {
                emit(initialData) // First value from Local
            }
            if (shouldFetch(initialData)) {
                val dataToLocal = remoteSource.fetchData(id)
                if (dataToLocal != null) {
                    localSource.createOrUpdate(dataToLocal)
                }
            }
            localSource.getAllObserveData().collect {
                emit(it)
            }
        }
    }

    override suspend fun getAllData(): Data? {
        return localSource.getAllData()
    }

    override suspend fun getById(id: String): Data? {
        return localSource.getById(id)
    }

    override suspend fun deleteById(id: String) {
        return localSource.delete(id)
    }

    override var id: String = EMPTY_STRING
}
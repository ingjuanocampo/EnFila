package com.ingjuanocampo.enfila.domain.usecases.repository.base

import kotlinx.coroutines.flow.Flow

interface Repository<Data> {

    var id: String

    suspend fun createOrUpdate(data: Data)

    suspend fun refresh(): Data?

    fun getAllObserveData(): Flow<Data?>

    suspend fun getAllData(): Data?

    suspend fun getById(id: String): Data?

    suspend fun delete(listOf: Data)

    suspend fun deleteById(id: String)

}


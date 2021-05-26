package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalSource: LocalSource<User> {
    var user: User? = null
    override suspend fun createOrUpdate(data: User) {
        this.user = data
    }

    override suspend fun getData(repoInfo: RepoInfo?): User? {
        return user
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<User?> {
        return flow { user }
    }

    override suspend fun delete(dataToDelete: User) {
        user = null
    }

    override suspend fun delete(id: String) {
    }

    override fun getAllObserveData(): Flow<User?> {
        return flow { user }
    }

    override suspend fun getAllData(): User? {
        return user
    }

    override suspend fun getById(id: String): User? {
        return user
    }
}
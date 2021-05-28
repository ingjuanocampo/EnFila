package com.ingjuanocampo.enfila.domain.data

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val remote: RemoteSource<User>,
private val localSource: LocalSource<User>): UserRepository, RepositoryImp<User>(remote, localSource) {
    override fun getFetchAndObserve(): Flow<User?> = remote.fetchInfoFlow(id)

}
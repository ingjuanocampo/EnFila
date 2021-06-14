package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.db.realm.Database
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.UserEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toModel
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalSource(val database: Database): LocalSource<User> {

    var user: User? = null
    override suspend fun createOrUpdate(data: User) {
        this.user = data
        database.realm.writeBlocking {
            copyToRealm(user?.toEntity()!!)
        }
    }

    override suspend fun delete(dataToDelete: User) {
        database.realm.objects<UserEntity>().delete()
    }

    override suspend fun delete(id: String) {
        database.realm.objects<UserEntity>().query("id = $0", id)
    }

    override fun getAllObserveData(): Flow<User?> {
        return flow { user }
    }

    override suspend fun getAllData(): User? {
        return try { database.realm.objects<UserEntity>().firstOrNull()?.toModel()} catch (e: Exception) {
            return null
        }
    }

    override suspend fun getById(id: String): User? {
        return try {database.realm.objects<UserEntity>().query("id = $0", id).firstOrNull()?.toModel()} catch (e: Exception) {null}
    }
}
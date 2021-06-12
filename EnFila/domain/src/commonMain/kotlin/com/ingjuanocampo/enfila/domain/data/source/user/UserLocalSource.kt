package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.db.realm.Database.realm
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalSource : LocalSource<User> {
    var user: User? = null
    override suspend fun createOrUpdate(data: User) {
        this.user = data
        realm.writeBlocking {
            copyToRealm(user!!)
        }
    }

    override suspend fun delete(dataToDelete: User) {
       realm.objects<User>().delete()
    }

    override suspend fun delete(id: String) {
        realm.objects<User>().query("id = $0", id)
    }

    override fun getAllObserveData(): Flow<User?> {
        return flow { user }
    }

    override suspend fun getAllData(): User? {
        return realm.objects<User>().firstOrNull()
    }

    override suspend fun getById(id: String): User? {
        return realm.objects<User>().query("id = $0", id).firstOrNull()
    }
}
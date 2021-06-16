package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.db.realm.Database
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.UserEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toModel
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UserLocalSource(val database: Database): LocalSource<User> {

    private val sharedFlow = MutableSharedFlow<User?>()

    init {
        database.realm.objects<UserEntity>().query().observe {
            sharedFlow.emitInContext(it.firstOrNull()?.toModel())
        }
    }

    override suspend fun createOrUpdate(data: User) {
        database.realm.writeBlocking {
            copyToRealm(data?.toEntity()!!)
        }
    }

    override suspend fun delete(dataToDelete: User) {
        database.realm.objects<UserEntity>().delete()
    }

    override suspend fun delete(id: String) {
        database.realm.objects<UserEntity>().query("id = $0", id)
    }

    override fun getAllObserveData(): Flow<User?> {
        return sharedFlow
    }

    override suspend fun getAllData(): User? {
        return try { database.realm.objects<UserEntity>().firstOrNull()?.toModel()} catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun getById(id: String): User? {
        return try {database.realm.objects<UserEntity>().query("id = $0", id).firstOrNull()?.toModel()} catch (e: Exception) {null}
    }
}

fun <T>MutableSharedFlow<T>.emitInContext(data: T) {
    val shareFlow = this
    GlobalScope.launch {
        shareFlow.emit(data)
    }
}


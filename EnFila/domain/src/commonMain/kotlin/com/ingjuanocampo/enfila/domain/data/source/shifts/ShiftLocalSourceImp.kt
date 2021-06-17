package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.data.source.db.realm.Database
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.ShiftEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toEntity
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.toModel
import com.ingjuanocampo.enfila.domain.data.source.user.emitInContext
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShiftLocalSourceImp(val database: Database) : ShiftLocalSource {

    private val flow = MutableSharedFlow<List<Shift>?>()

    private var isSubscribed: Boolean = false

    private fun observeAll(): MutableSharedFlow<List<Shift>?> {
        if (!isSubscribed) {
            database.get().objects<ShiftEntity>().query().observe { result ->
                flow.emitInContext(result.map { it.toModel() })
            }
        }
        isSubscribed = true
        return flow
    }

    override fun getClosestShift(): Flow<Shift?> {
        return observeAll().map { list ->
            list?.firstOrNull { it.state == ShiftState.WAITING }
        }
    }

    override fun getLastShift(): Flow<Shift?> {
        return observeAll().map { it?.lastOrNull() }
    }

    override fun getCallingShift(): Flow<Shift?> {
        return observeAll().map { list ->
            list?.firstOrNull { it.state == ShiftState.CALLING }
        }
    }

    override suspend fun createOrUpdate(data: List<Shift>) {
        database.get().writeBlocking {
            data.forEach {
                copyToRealm(it.toEntity())
            }
        }
    }

    override suspend fun delete(dataToDelete: List<Shift>) {
        dataToDelete.forEach {
            database.get().objects<ShiftEntity>().query("id = $0", it.id)
        }
    }

    override suspend fun delete(id: String) {
        database.get().objects<ShiftEntity>().query("id = $0", id)
    }

    override fun getAllObserveData(): Flow<List<Shift>?> {
        return observeAll()
    }

    override suspend fun getAllData(): List<Shift>? {
        return observeAll().firstOrNull()
    }

    override suspend fun getById(id: String): List<Shift>? {
        return database.get().objects<ShiftEntity>().query("id = $0", id)?.map { it.toModel() }
    }
}
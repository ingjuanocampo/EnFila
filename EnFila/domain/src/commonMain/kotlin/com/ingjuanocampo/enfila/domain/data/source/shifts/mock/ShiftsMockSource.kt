package com.ingjuanocampo.enfila.domain.data.source.shifts.mock

import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.data.source.shifts.ShiftLocalSource
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftFactory
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


// Constant mock data
private val list = arrayListOf(
    ShiftFactory.createWaiting(1, "1", "Fast please"),
    ShiftFactory.createWaiting(2, "2", "Fast please"),
    ShiftFactory.createWaiting(3, "3", "Fast please"),
    ShiftFactory.createWaiting(4, "4", "Fast please"),
    ShiftFactory.createWaiting(5, "5", "Fast please")
)

class ShiftsMockSource : ShiftLocalSource {

    val flow = MutableSharedFlow<List<Shift>>(3)

    init {
        GlobalScope.launch {
            flow.emit(list)
        }
    }

    override fun getClosestShift(): Flow<Shift?> {
        return flow.map { list ->
            list?.firstOrNull { it.state == ShiftState.WAITING }
        }
    }

    override fun getCallingShift(): Flow<Shift?> {
        return flow.map { list ->
             list?.firstOrNull { it.state == ShiftState.CALLING }
        }

    }


    override suspend fun createOrUpdate(data: List<Shift>) {
        data.forEach {
            if (list.contains(it)) {
                val index = list.indexOf(it)
                list.removeAt(index)
                list.add(index, it)
            } else list.add(it)
        }
        flow.emit(list)
    }

    override suspend fun getData(repoInfo: RepoInfo?): List<Shift> {
        return list
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<List<Shift>> {
        return flow
    }

    override suspend fun delete(dataToDelete: List<Shift>) {
        list.remove(dataToDelete)
        flow.tryEmit(list)
    }

    override fun getAllObserveData(): Flow<List<Shift>> {
        return flow
    }

    override suspend fun getAllData(): List<Shift> {
        return list
    }

    override suspend fun getById(id: String): List<Shift> {
        return list.filter { it.id == id }
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }
}
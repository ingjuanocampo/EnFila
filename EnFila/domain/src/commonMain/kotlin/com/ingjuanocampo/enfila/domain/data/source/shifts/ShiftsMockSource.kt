package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RepoInfo
import com.ingjuanocampo.enfila.domain.model.Shift
import com.ingjuanocampo.enfila.domain.model.ShiftFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow


// Constant mock data
private val list = arrayListOf(
    ShiftFactory.createWaiting(1, "1", "Fast please"),
    ShiftFactory.createWaiting(2, "2", "Fast please"),
    ShiftFactory.createWaiting(3, "3", "Fast please"),
    ShiftFactory.createWaiting(4, "4", "Fast please"),
    ShiftFactory.createWaiting(5, "5", "Fast please"))

class ShiftsMockSource: LocalSource<List<Shift>> {

    val flow =  MutableStateFlow<List<Shift>>(list)

    override fun createOrUpdate(data: List<Shift>) {
        list.addAll(data)
        flow.tryEmit(list)
    }

    override fun getData(repoInfo: RepoInfo?): List<Shift> {
        return list
    }

    override fun getDataAndObserve(repoInfo: RepoInfo?): Flow<List<Shift>> {
        return flow
    }

    override fun delete(dataToDelete: List<Shift>) {
        list.remove(dataToDelete)
        flow.tryEmit(list)
    }
}
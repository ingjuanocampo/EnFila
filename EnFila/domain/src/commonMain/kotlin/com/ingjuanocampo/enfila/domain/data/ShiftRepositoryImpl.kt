package com.ingjuanocampo.enfila.domain.data

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.data.source.shifts.ShiftLocalSource
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import kotlinx.coroutines.flow.Flow

class ShiftRepositoryImpl(
    private val remoteSource: RemoteSource<List<Shift>>,
    private val localSource: ShiftLocalSource
): ShiftRepository, RepositoryImp<List<Shift>>(remoteSource, localSource) {

    override fun getClosestShift(): Flow<Shift?> {
        return localSource.getClosestShift()
    }

    override fun getCallingShift(): Flow<Shift?> {
        return localSource.getCallingShift()
    }
}
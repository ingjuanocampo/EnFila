package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.ingjuanocampo.enfila.domain.data.source.LocalSource
import com.ingjuanocampo.enfila.domain.entity.Shift
import kotlinx.coroutines.flow.Flow

interface ShiftLocalSource: LocalSource<List<Shift>> {
    fun getClosestShift(): Flow<Shift?>

    fun getCallingShift(): Flow<Shift?>
}
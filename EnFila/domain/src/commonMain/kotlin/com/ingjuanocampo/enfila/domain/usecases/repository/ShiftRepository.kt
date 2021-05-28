package com.ingjuanocampo.enfila.domain.usecases.repository

import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import com.ingjuanocampo.enfila.domain.entity.Shift
import kotlinx.coroutines.flow.Flow

interface ShiftRepository: Repository<List<Shift>> {

    fun getClosestShift(): Flow<Shift?>

    fun getLastShift(): Flow<Shift?>

    fun getCallingShift(): Flow<Shift?>
}
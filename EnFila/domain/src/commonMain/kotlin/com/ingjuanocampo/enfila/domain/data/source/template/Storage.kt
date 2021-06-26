package com.ingjuanocampo.enfila.domain.data.source.template

import kotlinx.coroutines.flow.Flow

interface Storage<T> {

    fun save(data: T)

    fun save(data: List<T>)

    fun getData() : List<T>

    fun observeData(): Flow<List<T>>

}
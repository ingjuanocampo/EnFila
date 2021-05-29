package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.flow.Flow

actual class UserRemoteSource actual constructor(){
    actual fun fetchData(id: String): Flow<User?> {
        TODO()
    }

    actual  fun updateData(data: User): Flow<User?> {
        TODO()
    }

}
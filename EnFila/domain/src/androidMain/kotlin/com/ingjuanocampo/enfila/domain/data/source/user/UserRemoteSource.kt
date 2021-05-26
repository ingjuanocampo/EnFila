package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.User

actual class UserRemoteSource actual constructor() {


    actual fun fetchData(id: String): User {
        TODO()
    }

    actual  fun updateData(data: User) {

    }

}
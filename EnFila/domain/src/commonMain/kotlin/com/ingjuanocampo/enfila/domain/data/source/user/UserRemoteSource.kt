package com.ingjuanocampo.enfila.domain.data.source.user

import com.ingjuanocampo.enfila.domain.data.source.RemoteSource
import com.ingjuanocampo.enfila.domain.entity.User

class UserRemoteImpl(private val userRemoteSource: UserRemoteSource): RemoteSource<User> {
     override fun fetchData(id: String): User = userRemoteSource.fetchData(id)

     override fun updateData(data: User) = userRemoteSource.updateData(data)
}

expect class UserRemoteSource() {
     fun fetchData(id: String): User
     fun updateData(data: User)

}
package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class SignInUC(private val userRepository: UserRepository) {

    suspend operator fun invoke(id: String): Flow<Boolean> {
        userRepository.id = id
        //userRepository.refresh()
        return userRepository.getFetchAndObserve().map {data ->
            data != null
        }
    }

    suspend fun createUserAndSignIn(user: User): Boolean {
        userRepository.createOrUpdate(user)
        return true
    }
}
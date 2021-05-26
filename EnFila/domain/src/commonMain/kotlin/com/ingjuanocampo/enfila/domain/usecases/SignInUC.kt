package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository

class SignInUC(private val userRepository: UserRepository) {

    suspend operator fun invoke(id: String): Boolean {
        userRepository.id = id
        userRepository.refresh()
        val data = userRepository.getAllData()
        return data != null
    }

    suspend fun createUserAndSignIn(user: User): Boolean {
        userRepository.createOrUpdate(user)
        return true
    }
}
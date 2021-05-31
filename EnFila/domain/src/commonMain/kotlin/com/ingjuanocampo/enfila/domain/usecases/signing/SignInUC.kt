package com.ingjuanocampo.enfila.domain.usecases.signing

import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SignInUC(private val userRepository: UserRepository) {


    operator fun invoke(id: String): Flow<AuthState> {
        userRepository.id = id
        return userRepository.getFetchAndObserve().map {data ->
            data != null
        }.map {
            if (it) {
               AuthState.Authenticated
            } else {
                AuthState.NewAccount
            }
        }
    }

    suspend fun createUserAndSignIn(user: User, companyName: String): Flow<AuthState> {
            return userRepository.createOrUpdateFlow(user).map { AuthState.Authenticated as AuthState }.catch { it ->
                emit(AuthState.AuthError(it))
            }

    }
}
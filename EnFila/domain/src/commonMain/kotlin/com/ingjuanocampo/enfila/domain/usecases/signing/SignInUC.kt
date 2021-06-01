package com.ingjuanocampo.enfila.domain.usecases.signing

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.entity.getNow
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class SignInUC(
    private val userRepository: UserRepository,
    private val companySiteRepository: Repository<List<CompanySite>>
) {

    operator fun invoke(id: String): Flow<AuthState> {
        userRepository.id = id
        return userRepository.getFetchAndObserve().map { data ->
            data != null
        }.map {
            if (it) {
                AuthState.Authenticated
            } else {
                AuthState.NewAccount(id)
            }
        }
    }

    suspend fun createUserAndSignIn(user: User, companyName: String): Flow<AuthState> {
        return companySiteRepository.createOrUpdateFlow(
            listOf(
                CompanySite(
                    id = getNow().toString() + "CompanyId",
                    name = companyName
                )
            )
        ).flatMapLatest {
            userRepository.createOrUpdateFlow(user).map { AuthState.Authenticated as AuthState }
                .catch { it ->
                    emit(AuthState.AuthError(it))
                }
        }

    }
}
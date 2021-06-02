package com.ingjuanocampo.enfila.domain.usecases.signing

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.User
import com.ingjuanocampo.enfila.domain.entity.getNow
import com.ingjuanocampo.enfila.domain.state.AppStateProvider
import com.ingjuanocampo.enfila.domain.usecases.repository.CompanyRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class SignInUC(
    private val userRepository: UserRepository,
    private val companySiteRepository: CompanyRepository,
    private val appStateProvider: AppStateProvider
) {

    operator fun invoke(id: String): Flow<AuthState> {
        userRepository.id = id
        return userRepository.getAllObserveData().flatMapLatest { user ->
            companySiteRepository.id = user?.companyIds?.firstOrNull() ?: EMPTY_STRING
            companySiteRepository.getAllObserveData().map { user }
        }.map { data ->
            data != null
        }.map {
            if (it) {
                appStateProvider.toLoggedState()
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
        ).flatMapLatest { companyList ->
            user.companyIds = companyList?.map { it.id }.orEmpty()
            userRepository.createOrUpdateFlow(user).map {
                appStateProvider.toLoggedState()
                AuthState.Authenticated as AuthState }
                .catch { it ->
                    emit(AuthState.AuthError(it))
                }
        }

    }
}
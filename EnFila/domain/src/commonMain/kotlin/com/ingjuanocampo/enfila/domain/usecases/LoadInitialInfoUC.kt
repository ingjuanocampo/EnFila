package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class LoadInitialInfoUC(private val userRepository: UserRepository,
                        private val shiftRepository: ShiftRepository) {

    suspend operator fun invoke()  = withContext(Dispatchers.Default) {
        val user = userRepository.getCurrent()
        if (user?.id != null && shiftRepository.id.isNullOrBlank().not()) {
            shiftRepository.refresh()?.collect {// Concat
            }
        }
    }
}
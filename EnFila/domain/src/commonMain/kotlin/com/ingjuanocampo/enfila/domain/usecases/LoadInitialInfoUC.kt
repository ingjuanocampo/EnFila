package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository

class LoadInitialInfoUC(private val userRepository: UserRepository,
                        private val shiftRepository: ShiftRepository) {

    suspend operator fun invoke() {
        userRepository.getCurrent()
    }
}
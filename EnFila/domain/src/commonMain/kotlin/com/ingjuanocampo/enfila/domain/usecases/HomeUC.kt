package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Client
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.usecases.list.isActive
import com.ingjuanocampo.enfila.domain.usecases.model.Home
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient
import com.ingjuanocampo.enfila.domain.usecases.repository.CompanyRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.UserRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class HomeUC(private val companyRepo: CompanyRepository,
             val userRepository: UserRepository,
             val shiftRepository: ShiftRepository,
             val shiftInteractions: ShiftInteractions
) {

    private var homeCache : Home? = null

    fun load(): Flow<Home> {
        return flow {

            val user = userRepository.getCurrent()
            companyRepo.id = user?.companyIds?.first()?: EMPTY_STRING
            val currentCompany = companyRepo.getAllData()
            val home = Home(selectedCompany = currentCompany?: CompanySite(),
                totalTurns = shiftRepository.getAllData()!!.filter { it.isActive() }.count(),
                avrTime = 306)

            homeCache = home

            emit(home)

            shiftRepository.getCallingShift().collect { shift ->
                shift?.let {
                    home.currentTurn = shiftInteractions.loadShiftWithClient(it)
                    home.totalTurns = shiftRepository.getAllData()!!.filter { it.isActive() }.count()
                    homeCache = home
                    emit(home)
                }
            }
        }
    }

    suspend fun next(): ShiftWithClient? {
        return homeCache?.currentTurn.let { shift ->
           shiftInteractions.next(shift?.shift)
        }
    }

    fun delete() {
    }


}
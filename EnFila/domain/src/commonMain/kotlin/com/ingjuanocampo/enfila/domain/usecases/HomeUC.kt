package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Client
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.usecases.list.isActive
import com.ingjuanocampo.enfila.domain.usecases.model.Home
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class HomeUC(private val companyRepo: Repository<List<CompanySite>>
             , val clientRepository: Repository<List<Client>>,
             val shiftRepository: ShiftRepository,
             val shiftInteractions: ShiftInteractions
) {

    private var homeCache : Home? = null

    fun load(): Flow<Home> {
        return flow {
            // TODO This id should come from a session
            val currentCompany = companyRepo.getById("companyid").first()
            val home = Home(selectedCompany = currentCompany,
                totalTurns = shiftRepository.getAllData().filter { it.isActive() }.count(),
                avrTime = 306)

            homeCache = home

            emit(home)

            shiftRepository.getCallingShift().collect { shift ->
                shift?.let {
                    home.currentTurn = shiftInteractions.loadShiftWithClient(it)
                    home.totalTurns = shiftRepository.getAllData().filter { it.isActive() }.count()
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
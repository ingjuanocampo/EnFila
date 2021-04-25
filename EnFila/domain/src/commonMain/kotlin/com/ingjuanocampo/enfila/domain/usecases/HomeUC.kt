package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Contact
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.usecases.model.Home
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.DateTimeUnit

class HomeUC(private val companyRepo: Repository<List<CompanySite>>
             , val contactRepository: Repository<List<Contact>>,
             val shiftRepository: ShiftRepository
) {

    private var homeCache : Home? = null

    fun load(): Flow<Home> {
        return flow {
            // TODO This id should come from a session
            val currentCompany = companyRepo.getById("companyid").first()
            val home = Home(selectedCompany = currentCompany,
                totalTurns = 200,
                avrTime = 300)

            homeCache = home

            emit(home)

            shiftRepository.getCallingShift().collect { shift ->
                shift?.let {
                    val client = contactRepository.getById(it.contactId).first()
                    home.currentTurn = ShiftWithClient(it, client)
                    homeCache = home
                    emit(home)
                }
            }
        }
    }


    suspend fun next(): ShiftWithClient? {
        homeCache?.currentTurn?.let { shift ->
            val current = shift.shift
            current?.state = ShiftState.FINISHED
            shiftRepository.createOrUpdate(listOf(current))

        }

        val closestShift = shiftRepository.getClosestShift().firstOrNull()
        closestShift?.state = ShiftState.CALLING

        closestShift?.let {
            shiftRepository.createOrUpdate(listOf(it))
        }
        return closestShift?.let {
            ShiftWithClient(
                it,
                contactRepository.getById(it.contactId).first())
        }
    }

    fun delete() {
    }


}
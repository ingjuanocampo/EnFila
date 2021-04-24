package com.ingjuanocampo.enfila.domain.usecases

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Contact
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.usecases.model.Home
import com.ingjuanocampo.enfila.domain.usecases.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class HomeUC(private val companyRepo: Repository<List<CompanySite>>
                 ,val contactRepository: Repository<Contact>,
val shiftRepository: Repository<Shift>) {

    fun loadHomeInfo(): Flow<Home> {
        return flow {
            // TODO This id should come from a session
            val currentCompany = companyRepo.getById("companyid")

            //shiftRepository.

        }
    }


}
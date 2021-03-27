package com.ingjuanocampo.enfila.domain.di.domain

import com.ingjuanocampo.enfila.domain.di.data.DataModule
import com.ingjuanocampo.enfila.domain.usecases.CompanySiteInteractions
import com.ingjuanocampo.enfila.domain.usecases.ShiftInteractions

class DomainModule(val dataModule: DataModule) {

    fun providesCompanySiteInteractions(): CompanySiteInteractions = CompanySiteInteractions(dataModule.shiftsRepository,
        dataModule.companySiteRepository)

    fun providesShiftInteractions(): ShiftInteractions = ShiftInteractions(dataModule.shiftsRepository)

}
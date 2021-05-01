package com.ingjuanocampo.enfila.domain.di.domain

import com.ingjuanocampo.enfila.domain.di.data.DataModule
import com.ingjuanocampo.enfila.domain.usecases.CompanySiteInteractions
import com.ingjuanocampo.enfila.domain.usecases.HomeUC
import com.ingjuanocampo.enfila.domain.usecases.ShiftInteractions
import com.ingjuanocampo.enfila.domain.usecases.list.ListUC

object DomainModule {

    fun providesCompanySiteInteractions(): CompanySiteInteractions = CompanySiteInteractions(DataModule.shiftsRepository,
        DataModule.companySiteRepository)

    fun providesShiftInteractions(): ShiftInteractions = ShiftInteractions(DataModule.shiftsRepository, DataModule.clientRepository)

    fun provideHomeUC() = HomeUC(DataModule.companySiteRepository, DataModule.clientRepository, DataModule.shiftsRepository,
        providesShiftInteractions())

    fun provideListUC() = ListUC(DataModule.shiftsRepository, providesShiftInteractions())



}
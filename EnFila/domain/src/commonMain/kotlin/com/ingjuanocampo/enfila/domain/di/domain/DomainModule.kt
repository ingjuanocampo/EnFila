package com.ingjuanocampo.enfila.domain.di.domain

import com.ingjuanocampo.enfila.domain.di.data.DataModule
import com.ingjuanocampo.enfila.domain.usecases.CompanySiteInteractions
import com.ingjuanocampo.enfila.domain.usecases.HomeUC
import com.ingjuanocampo.enfila.domain.usecases.ShiftInteractions

object DomainModule {

    fun providesCompanySiteInteractions(): CompanySiteInteractions = CompanySiteInteractions(DataModule.shiftsRepository,
        DataModule.companySiteRepository)

    fun providesShiftInteractions(): ShiftInteractions = ShiftInteractions(DataModule.shiftsRepository)

    fun provideHomeUC() = HomeUC(DataModule.companySiteRepository, DataModule.contactRepository, DataModule.shiftsRepository)

}
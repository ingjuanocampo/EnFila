package com.ingjuanocampo.enfila.domain.di.domain

import com.ingjuanocampo.enfila.domain.Platform
import com.ingjuanocampo.enfila.domain.di.data.DataModule
import com.ingjuanocampo.enfila.domain.state.AppStateProvider
import com.ingjuanocampo.enfila.domain.usecases.HomeUC
import com.ingjuanocampo.enfila.domain.usecases.ShiftInteractions
import com.ingjuanocampo.enfila.domain.usecases.signing.SignInUC
import com.ingjuanocampo.enfila.domain.usecases.list.ListUC

object DomainModule {

    var appPlatform : Platform ? = null
    set(value) {
        DataModule.appPlatform = value
    }

    fun providesShiftInteractions(): ShiftInteractions = ShiftInteractions(DataModule.shiftsRepository, DataModule.clientRepository)

    fun provideHomeUC() = HomeUC(DataModule.companySiteRepository, DataModule.userRepository, DataModule.shiftsRepository,
        providesShiftInteractions())

    fun provideListUC() = ListUC(DataModule.shiftsRepository, providesShiftInteractions())

    fun provideSignUC(appStateProvider: AppStateProvider) = SignInUC(DataModule.userRepository, DataModule.companySiteRepository, appStateProvider)

    fun provideIsUserLoggedMethod(): () -> Boolean {
        return {
            DataModule.userRepository.isUserLogged()
        }
    }



}
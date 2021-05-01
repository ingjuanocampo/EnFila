package com.ingjuanocampo.enfila.domain.di.data

import com.ingjuanocampo.enfila.domain.data.RepositoryImp
import com.ingjuanocampo.enfila.domain.data.ShiftRepositoryImpl
import com.ingjuanocampo.enfila.domain.data.source.companysite.CompanySiteMockSource
import com.ingjuanocampo.enfila.domain.data.source.companysite.CompanySiteRemoteSource
import com.ingjuanocampo.enfila.domain.data.source.contact.ContactMockSource
import com.ingjuanocampo.enfila.domain.data.source.contact.ContactRemoteSource
import com.ingjuanocampo.enfila.domain.data.source.shifts.mock.ShiftsMockSource
import com.ingjuanocampo.enfila.domain.data.source.shifts.ShiftsRemoteSource
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Client
import com.ingjuanocampo.enfila.domain.usecases.repository.ShiftRepository
import com.ingjuanocampo.enfila.domain.usecases.repository.base.Repository

internal object DataModule {

    val companySiteRepository: Repository<List<CompanySite>> by lazy {
        RepositoryImp(remoteSource = CompanySiteRemoteSource(),
        localSource = CompanySiteMockSource())
    }

    val clientRepository: Repository<List<Client>> by lazy {
        RepositoryImp(remoteSource = ContactRemoteSource(),
            localSource = ContactMockSource())
    }

    val shiftsRepository: ShiftRepository by lazy {
        ShiftRepositoryImpl(remoteSource = ShiftsRemoteSource(),
            localSource = ShiftsMockSource()
        )
    }

}
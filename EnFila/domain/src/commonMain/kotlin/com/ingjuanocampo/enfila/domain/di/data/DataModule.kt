package com.ingjuanocampo.enfila.domain.di.data

import com.ingjuanocampo.enfila.domain.data.RepositoryImp
import com.ingjuanocampo.enfila.domain.data.source.companysite.CompanySiteMockSource
import com.ingjuanocampo.enfila.domain.data.source.companysite.CompanySiteRemoteSource
import com.ingjuanocampo.enfila.domain.data.source.contact.ContactMockSource
import com.ingjuanocampo.enfila.domain.data.source.contact.ContactRemoteSource
import com.ingjuanocampo.enfila.domain.data.source.shifts.ShiftsMockSource
import com.ingjuanocampo.enfila.domain.data.source.shifts.ShiftsRemoteSource
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Contact
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.usecases.repository.Repository

internal object DataModule {

    val companySiteRepository: Repository<List<CompanySite>> by lazy {
        RepositoryImp(remoteSource = CompanySiteRemoteSource(),
        localSource = CompanySiteMockSource())
    }

    val contactRepository: Repository<List<Contact>> by lazy {
        RepositoryImp(remoteSource = ContactRemoteSource(),
            localSource = ContactMockSource())
    }

    val shiftsRepository: Repository<List<Shift>> by lazy {
        RepositoryImp(remoteSource = ShiftsRemoteSource(),
            localSource = ShiftsMockSource())
    }

}
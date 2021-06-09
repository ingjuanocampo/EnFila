package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow

actual class CompanyInfoRemoteSource actual constructor() {

    actual fun fetchData(id: String): Flow<CompanySite?> {
        TODO()
    }
    actual fun updateData(data: CompanySite): Flow<CompanySite?> {
        TODO()
    }
}
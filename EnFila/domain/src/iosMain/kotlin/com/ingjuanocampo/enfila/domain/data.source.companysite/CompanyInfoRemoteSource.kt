package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow

actual class CompanyInfoRemoteSource actual constructor() {

    actual fun fetchData(id: String): Flow<List<CompanySite>?> {
        TODO()
    }
    actual fun updateData(data: List<CompanySite>): Flow<List<CompanySite>?> {
        TODO()
    }
}
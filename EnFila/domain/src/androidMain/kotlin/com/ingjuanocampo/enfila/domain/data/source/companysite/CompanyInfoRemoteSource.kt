package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.data.source.user.basePath
import com.ingjuanocampo.enfila.domain.data.util.fetchProcess
import com.ingjuanocampo.enfila.domain.data.util.uploadProcess
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.flow.Flow

private const val companyInfoPath = basePath + "_company"

actual class CompanyInfoRemoteSource actual constructor() {

    val db = Firebase.firestore

    actual fun fetchData(id: String): Flow<CompanySite?> {
        return db.fetchProcess({ data ->
            return@fetchProcess CompanySite(
                    id = id,
                    name = data["name"] as String?,
                    shiftsIdList = data["shiftList"] as List<String>?)
        }, companyInfoPath, id)
    }

    actual fun updateData(data: CompanySite): Flow<CompanySite?> {
        return db.uploadProcess({
            return@uploadProcess hashMapOf(
                "id" to data.id,
                "name" to data.name,
                "shiftList" to data.shiftsIdList
            )
        }, data, companyInfoPath, data.id)
    }
}

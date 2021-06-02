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

    actual fun fetchData(id: String): Flow<List<CompanySite>?> {
        return db.fetchProcess({ data ->
            return@fetchProcess arrayListOf(
                CompanySite(
                    id = id,
                    name = data.get("name") as String?,
                    shiftsIdList = data.get("shiftList") as List<String>
                )
            )
        }, companyInfoPath, id)
    }

    actual fun updateData(data: List<CompanySite>): Flow<List<CompanySite>?> {
        val data2 = data[0]
        return db.uploadProcess({
            return@uploadProcess hashMapOf(
                "name" to data2,
                "shiftList" to data2.shiftsIdList
            )
        }, data, companyInfoPath, data2.id)
    }
}

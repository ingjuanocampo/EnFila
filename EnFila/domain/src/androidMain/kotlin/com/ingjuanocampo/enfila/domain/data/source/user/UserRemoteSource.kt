package com.ingjuanocampo.enfila.domain.data.source.user

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.data.util.fetchProcess
import com.ingjuanocampo.enfila.domain.data.util.uploadProcess
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.flow.Flow

const val basePath = "enfila_v1"

private const val userPath = basePath + "_users"

actual class UserRemoteSource actual constructor() {

    val db = Firebase.firestore

    actual fun fetchData(id: String): Flow<User?> {
        return db.fetchProcess({ data ->
            return@fetchProcess User(
                id = id,
                name = data.get("name") as String?,
                phone = data.get("phone") as String,
                companyIds = data.get("companyIds") as? List<String>
            )
        }, userPath, id)
    }

    actual fun updateData(data: User): Flow<User?> {
        return db.uploadProcess({
            return@uploadProcess hashMapOf(
                "name" to data.name,
                "phone" to data.phone,
                "companyIds" to data.companyIds
            )
        }, data, userPath, data.id)
    }
}
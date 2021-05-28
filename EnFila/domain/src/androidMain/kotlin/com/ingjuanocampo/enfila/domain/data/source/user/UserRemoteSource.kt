package com.ingjuanocampo.enfila.domain.data.source.user

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

actual class UserRemoteSource actual constructor() {

    val db = Firebase.firestore

    actual fun fetchData(id: String): Flow<User?> {

        val sharedFlow = MutableSharedFlow<User?>()

        db.collection("enfila_v1_users").document(id)
            .get().addOnSuccessListener { result ->
                GlobalScope.launch {
                    sharedFlow.emit(
                        User(
                            id = id,
                            name = result.data?.get("name") as String?,
                            phone = result.data?.get("phone") as String,
                            companyIds = result.data?.get("companyIds") as List<String>
                        )
                    )
                }

            }.addOnFailureListener {
                GlobalScope.launch {
                    sharedFlow.emit(null)
                }
            }
        return sharedFlow
    }


    actual fun updateData(data: User) {

    }

}
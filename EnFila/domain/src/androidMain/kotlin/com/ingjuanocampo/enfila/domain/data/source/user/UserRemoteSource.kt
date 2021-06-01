package com.ingjuanocampo.enfila.domain.data.source.user

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

const val basePath = "enfila_v1"

private const val userPath = basePath + "_users"
actual class UserRemoteSource actual constructor() {

    val db = Firebase.firestore

    actual fun fetchData(id: String): Flow<User?> {

        val sharedFlow = MutableSharedFlow<User?>()

        db.collection(userPath).document(id)
            .get().addOnSuccessListener { result ->
                GlobalScope.launch {
                    try {
                        sharedFlow.emit(
                            User(
                                id = id,
                                name = result.data?.get("name") as String?,
                                phone = result.data?.get("phone") as String,
                                companyIds = result.data?.get("companyIds") as? List<String>
                            )
                        )
                    } catch (e: Exception) {
                        sharedFlow.emit(null)
                    }
                }

            }.addOnFailureListener {
                GlobalScope.launch {
                    sharedFlow.emit(null)
                }
            }
        return sharedFlow
    }

    actual fun updateData(data: User): Flow<User?> {
        val sharedFlow = MutableSharedFlow<User?>()
        val user = hashMapOf(
            "name" to data.name,
            "phone" to data.phone)
        db.collection(userPath)
            .document(data.id)
            .set(user)
            .addOnSuccessListener { documentReference ->
                GlobalScope.launch {
                    sharedFlow.emit(data)
                }
            }
            .addOnFailureListener { e ->
                GlobalScope.launch {
                    sharedFlow.emit(null)

                }
            }
        return sharedFlow
    }
}
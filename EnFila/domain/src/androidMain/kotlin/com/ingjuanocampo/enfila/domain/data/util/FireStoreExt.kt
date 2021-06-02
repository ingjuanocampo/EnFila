package com.ingjuanocampo.enfila.domain.data.util

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

fun<T> FirebaseFirestore.uploadProcess(dataMapper: (T)-> Any, data: T, path: String, id: String): Flow<T?> {
    val sharedFlow = MutableSharedFlow<T?>()
    val toUpload = dataMapper(data)
    this.collection(path)
        .document(id)
        .set(toUpload)
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


fun<T> FirebaseFirestore.fetchProcess(dataMapper: (Map<String, Any>)-> T, path: String, id: String): Flow<T?> {
    val sharedFlow = MutableSharedFlow<T?>()
    try {
        this.collection(path).document(id)
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    try {
                        sharedFlow.emit(
                            dataMapper(result.data!!)
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
    } catch (e: Exception) {
        GlobalScope.launch {
            sharedFlow.emit(null)
        }
    }

    return sharedFlow
}


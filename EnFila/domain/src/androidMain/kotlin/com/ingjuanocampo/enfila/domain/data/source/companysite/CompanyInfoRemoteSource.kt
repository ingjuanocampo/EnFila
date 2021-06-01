package com.ingjuanocampo.enfila.domain.data.source.companysite

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.data.source.user.basePath
import com.ingjuanocampo.enfila.domain.entity.CompanySite
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

private const val companyInfoPath = basePath +"_company"
actual class CompanyInfoRemoteSource actual constructor() {

    val db = Firebase.firestore

    actual fun fetchData(id: String): Flow<List<CompanySite>?> {
        val sharedFlow = MutableSharedFlow<List<CompanySite>?>()

        db.collection(companyInfoPath).document(id)
            .get().addOnSuccessListener { result ->
                GlobalScope.launch {
                    try {
                        sharedFlow.emit(
                            arrayListOf(CompanySite(
                                id = id,
                                name = result.data?.get("name") as String?,
                                shiftsIdList = result.data?.get("shiftList") as List<String>
                            ))
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
    actual fun updateData(data: List<CompanySite>): Flow<List<CompanySite>?> {
        val sharedFlow = MutableSharedFlow<List<CompanySite>?>()
        val data2 = data[0]

        val company = hashMapOf(
            "name" to data2,
            "shiftList" to data2.shiftsIdList)
        db.collection(companyInfoPath)
            .document(data2.id)
            .set(company)
            .addOnSuccessListener { documentReference ->
                GlobalScope.launch {
                    sharedFlow.emit(listOf(data2))
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


// TODO TO Test with this generic process
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


fun<T> FirebaseFirestore.fetchProcess(dataMapper: (Any)-> T, path: String, id: String): Flow<T?> {
    val sharedFlow = MutableSharedFlow<T?>()
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
    return sharedFlow
}


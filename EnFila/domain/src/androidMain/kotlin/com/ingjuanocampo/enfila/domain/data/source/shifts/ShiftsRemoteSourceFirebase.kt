package com.ingjuanocampo.enfila.domain.data.source.shifts

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ingjuanocampo.enfila.domain.data.source.companysite.companyInfoPath
import com.ingjuanocampo.enfila.domain.data.util.fetchProcessMultiples
import com.ingjuanocampo.enfila.domain.data.util.uploadProcess
import com.ingjuanocampo.enfila.domain.data.util.uploadProcessMultiples
import com.ingjuanocampo.enfila.domain.entity.Shift
import com.ingjuanocampo.enfila.domain.entity.ShiftState
import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.coroutines.flow.Flow

actual class ShiftsRemoteSourceFirebase {
    private val remote = Firebase.firestore
    private val shiftPath = "shifts"

    actual fun fetchData(id: String): Flow<List<Shift>?> {
        return remote.fetchProcessMultiples({
            Shift(
                date = it["date"] as Long? ?: 0L,
                id = it["id"] as String,
                parentCompanySite = it["parentCompanySite"] as String? ?: EMPTY_STRING,
                number = it["number"] as Int? ?: 0,
                contactId = it["contactId"] as String? ?: EMPTY_STRING,
                notes = it["notes"] as String? ?: EMPTY_STRING,
                state = getState(it["state"] as Int?)
            )
        }, getPath(id))
    }

    private fun getPath(parentCompanySite: String) = "$companyInfoPath/$parentCompanySite/$shiftPath"

    private fun getState(value: Int?): ShiftState {
        return ShiftState.values().firstOrNull { it.ordinal == value ?: 0 } ?: ShiftState.WAITING
    }


    actual fun updateData(data: Shift): Flow<Shift?> {
        return remote.uploadProcess({

        }, data, getPath(data.parentCompanySite), data.id)
    }

    actual fun updateData(data: List<Shift>): Flow<List<Shift>?> {
        return remote.uploadProcessMultiples({
            return@uploadProcessMultiples mapShift(it)
        }, data, getPath(data.first().parentCompanySite))
    }

    private fun mapShift(it: Shift) = hashMapOf(
        "date" to it.date,
        "id" to it.id,
        "parentCompanySite" to it.parentCompanySite,
        "number" to it.number,
        "contactId" to it.contactId,
        "notes" to it.notes,
        "state" to it.state.ordinal)
}



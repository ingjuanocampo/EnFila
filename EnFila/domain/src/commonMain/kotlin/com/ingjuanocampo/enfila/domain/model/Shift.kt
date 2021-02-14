package com.ingjuanocampo.enfila.domain.model

import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.datetime.Clock

class Shift internal constructor(
    val date: Long?,
    val id: String? = EMPTY_STRING,
    val parentCompanySite: String,
    var number: Int = 0,
    val contactId: String?,
    val notes: String?,
    val state: ShiftState
) : Comparable<Shift> {

    override fun compareTo(other: Shift): Int {
        val numberCompare = this.number.compareTo(other.number)
        if (numberCompare == 0) {
            return state.compareTo(other.state)
        }
        return numberCompare
    }

}


object ShiftFactory {

    var currentCompanySiteId: String = "companyid"// for testing

    fun createWaiting(number: Int,
                      contactId: String,
                      notes: String): Shift {
        val instantNow = Clock.System.now().epochSeconds
        return Shift(
            date = instantNow,
            id = instantNow.toString(),
            parentCompanySite = currentCompanySiteId,
            number = number,
            contactId = contactId,
            notes = notes,
            state = ShiftState.WAITING
        )
    }
}
package com.ingjuanocampo.enfila.domain.model

import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING

class CompanySite(
    private val shifts: ArrayList<Shift> = arrayListOf(),// Move this value away
    private val id: String? = EMPTY_STRING,
    private val name: String? = EMPTY_STRING
) {

    fun next(): Shift {
        shifts.sort()
        if (shifts.isEmpty()) throw IllegalStateException("No Items")

        val possibleNextShift = shifts[0]

        if (possibleNextShift.state == ShiftState.WAITING) {
            return possibleNextShift
        } else throw IllegalStateException("No items")
    }

    fun add(shift: Shift) {
        shifts.add(shift)
    }

    fun getActiveShifts(): List<Shift> {
        return shifts.filter { it.state == ShiftState.WAITING }.sorted()
    }

    fun getHistory(): List<Shift> {
        return shifts.filter { it.state != ShiftState.WAITING }.sorted()
    }

    fun getCurrentTurn(): Shift? {
        return shifts.firstOrNull { it.state == ShiftState.CALLING }
    }


}

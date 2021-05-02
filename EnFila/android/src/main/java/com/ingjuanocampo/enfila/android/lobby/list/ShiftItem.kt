package com.ingjuanocampo.enfila.android.lobby.list

import android.util.Log
import com.ingjuanocampo.cdapter.RecyclerViewType
import com.ingjuanocampo.enfila.android.utils.ViewTypes
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


data class ShiftItem(val id: Int,
                     val phone: String,
                     val name: String,
                     val currentTurn: String,
                     val issueDate: Long,
                     val state: String
): RecyclerViewType {

    fun getDiffTime(): Long {
        val current = Date().time
        val elapsedTime = current - issueDate
        Log.d("Shift_Item", SimpleDateFormat("hh:mm").format(Date(elapsedTime)))
        return elapsedTime
    }

    fun getDiffTimeString(): String {
        val simpleDateFormat = SimpleDateFormat("hh:mm")
        return simpleDateFormat.format(Date().apply {
            time = getDiffTime()
        })
    }

    override fun getDelegateId(): Int = id

    override fun getViewType(): Int = ViewTypes.SHIFT.ordinal
}
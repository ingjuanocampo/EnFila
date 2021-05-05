package com.ingjuanocampo.enfila.android.lobby.list

import android.util.Log
import com.ingjuanocampo.cdapter.RecyclerViewType
import com.ingjuanocampo.enfila.android.utils.ViewTypes
import com.ingjuanocampo.enfila.domain.entity.getNow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


data class ShiftItem(val id: Int,
                     val phone: String,
                     val name: String,
                     val currentTurn: String,
                     val issueDate: Long,
                     val endDate: Long,
                     val state: String
): RecyclerViewType {

    fun getDiffTime(): Long {
        val current = getNow()
        val elapsedTime = current - issueDate
        //Log.d("Shift_Item", SimpleDateFormat("hh:mm").format(Date(elapsedTime)))
        return elapsedTime
    }

    fun getStringEndDate(): String {
        return SimpleDateFormat("hh:mm aa").format(Date(TimeUnit.SECONDS.toMillis(endDate)))
    }

    fun getDiffTimeString(): String {
        var diff = getDiffTime()

        val day = 60 * 60 * 24
        val hour = 60 * 60
        val minute = 60
        val numOfDays = (diff / (day)).toInt()
        diff %= day

        val hours = (diff / (hour)).toInt()
        diff %= hour

        val minutes = (diff / (minute)).toInt()
        diff %= minute

        val seconds = (diff).toInt()
        return "$hours:$minutes:$seconds"
    }

    override fun getDelegateId(): Int = id

    override fun getViewType(): Int = ViewTypes.SHIFT.ordinal
}
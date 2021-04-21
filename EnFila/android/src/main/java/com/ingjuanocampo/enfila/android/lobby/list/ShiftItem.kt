package com.ingjuanocampo.enfila.android.lobby.list

import com.ingjuanocampo.cdapter.RecyclerViewType
import com.ingjuanocampo.enfila.android.utils.ViewTypes

data class ShiftItem(val id: Int,
                     val phone: String,
                     val name: String,
                     val currentTurn: String
): RecyclerViewType {
    override fun getDelegateId(): Int = id

    override fun getViewType(): Int = ViewTypes.SHIFT.ordinal
}
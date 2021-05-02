package com.ingjuanocampo.enfila.android.lobby.list.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.ingjuanocampo.cdapter.DelegateViewHolder
import com.ingjuanocampo.cdapter.RecyclerViewType
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.list.ShiftItem
import com.ingjuanocampo.enfila.android.utils.inflate


class DelegateShift(parent: ViewGroup):
    DelegateViewHolder(parent.inflate(R.layout.delegate_shift)) {
    private val currentTurn: TextView = itemView.findViewById(R.id.currentTurn)
    private val number: TextView = itemView.findViewById(R.id.number)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val state: TextView = itemView.findViewById(R.id.state)
    private val timeElapsed: TextView = itemView.findViewById(R.id.timeElapse)

    override fun onBindViewHolder(recyclerViewType: RecyclerViewType) {
        val shiftItem = recyclerViewType as ShiftItem
        currentTurn.text = shiftItem.currentTurn
        number.text = shiftItem.phone
        name.text = shiftItem.name
        state.text = shiftItem.state
        timeElapsed.text = shiftItem.getDiffTimeString()


    }
}
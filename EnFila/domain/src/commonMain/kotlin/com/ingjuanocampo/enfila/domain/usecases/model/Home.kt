package com.ingjuanocampo.enfila.domain.usecases.model

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Contact
import com.ingjuanocampo.enfila.domain.entity.Shift

class Home(val selectedCompany: CompanySite,
           var totalTurns: Int,
           var avrTime: Long) {
    var currentTurn: ShiftWithClient? = null
}

data class ShiftWithClient(val shift: Shift, val user: Contact)
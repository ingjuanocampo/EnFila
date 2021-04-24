package com.ingjuanocampo.enfila.domain.usecases.model

import com.ingjuanocampo.enfila.domain.entity.CompanySite
import com.ingjuanocampo.enfila.domain.entity.Contact
import com.ingjuanocampo.enfila.domain.entity.Shift

class Home(val selectedCompany: CompanySite,
           var currentTurn: ShiftWithUser,
           var totalTurns: Int,
           var avrTime: Long)

data class ShiftWithUser(val shift: Shift, val user: Contact)
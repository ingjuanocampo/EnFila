package com.ingjuanocampo.enfila.domain.model

import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING

data class CompanySite(
     val id: String = EMPTY_STRING,
     val name: String? = EMPTY_STRING
) {
    var shifts: ArrayList<Shift> = arrayListOf()
}

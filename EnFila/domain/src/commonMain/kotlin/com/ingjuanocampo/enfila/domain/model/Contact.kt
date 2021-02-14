package com.ingjuanocampo.enfila.domain.model

import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING
import kotlinx.datetime.Clock

data class Contact(val id: String = Clock.System.now().epochSeconds.toString(),
                   val name: String? = EMPTY_STRING,
                   val phone : String? = EMPTY_STRING,
                   val shifts: ArrayList<String> = ArrayList())
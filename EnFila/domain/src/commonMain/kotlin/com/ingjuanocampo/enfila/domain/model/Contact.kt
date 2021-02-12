package com.ingjuanocampo.enfila.domain.model

import com.ingjuanocampo.enfila.domain.util.EMPTY_STRING

data class Contact(val id: String? = EMPTY_STRING,
val name: String? = EMPTY_STRING,
val phone : String? = EMPTY_STRING,
val shifts: ArrayList<String> = ArrayList())

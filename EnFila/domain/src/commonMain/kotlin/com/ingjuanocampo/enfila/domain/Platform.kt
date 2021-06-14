package com.ingjuanocampo.enfila.domain

expect class Platform(context: Any) {
    val platform: String
    val basePath: String
}
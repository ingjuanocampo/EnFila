package com.ingjuanocampo.enfila.domain.data.source.db.realm

import com.ingjuanocampo.enfila.domain.Platform
import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.UserEntity
import io.realm.Realm
import io.realm.RealmConfiguration

class Database(private val platform: Platform) {

    val realm: Realm by lazy {
        val configuration = RealmConfiguration(schema = setOf(UserEntity::class))
        Realm.open(configuration)
    }
}
package com.ingjuanocampo.enfila.domain.data.source.db.realm

import com.ingjuanocampo.enfila.domain.data.source.db.realm.entity.UserEntity
import com.ingjuanocampo.enfila.domain.entity.User
import io.realm.Realm
import io.realm.RealmConfiguration

object Database {

    private val configuration = RealmConfiguration(schema = setOf(UserEntity::class))

    val realm = Realm.open(configuration)


}
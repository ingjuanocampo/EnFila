package com.ingjuanocampo.enfila.domain

import android.content.Context

actual class Platform actual constructor(val context: Any){
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val basePath = (context as Context).filesDir.toString() + "myfile/"
}
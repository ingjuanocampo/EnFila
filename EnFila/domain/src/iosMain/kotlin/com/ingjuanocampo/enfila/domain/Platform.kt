package com.ingjuanocampo.enfila.domain


import platform.UIKit.UIDevice

actual class Platform actual constructor(val context: Any) {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val basePath: String
        get() = ""
}
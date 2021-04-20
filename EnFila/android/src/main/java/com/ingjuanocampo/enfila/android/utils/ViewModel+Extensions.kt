package com.ingjuanocampo.enfila.android.utils

import kotlinx.coroutines.*


val handler = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
}

fun CoroutineScope.launchGeneral(function: suspend () -> Unit) {
    launch(context = handler) {
        withContext(Dispatchers.Default) {
            function()
        }
    }
}
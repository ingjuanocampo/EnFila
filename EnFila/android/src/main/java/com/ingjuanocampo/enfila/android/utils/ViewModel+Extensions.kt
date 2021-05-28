package com.ingjuanocampo.enfila.android.utils

import android.util.Log
import kotlinx.coroutines.*


val handler = CoroutineExceptionHandler { _, exception ->
    Log.d("CoroutineExceptionHandler", "got $exception")
}

fun CoroutineScope.launchGeneral(function: suspend () -> Unit) {
    launch(handler + Dispatchers.Default) {
        withContext(Dispatchers.Default) {
            function()
        }
    }
}
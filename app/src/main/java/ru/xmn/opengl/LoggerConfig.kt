package ru.xmn.opengl

import android.util.Log

/**
 * Created by xmn on 24.09.2017.
 */
object LoggerConfig {
    var ON = true
}

fun log(tag: String, message: String){
    if (LoggerConfig.ON){
        Log.w(tag, message)
    }
}
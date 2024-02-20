package com.example.core.extensions

import android.content.res.Resources
import androidx.annotation.ArrayRes



fun Int.formEnding(resources: Resources, @ArrayRes stringArray: Int): String =
    this.toLong().formEnding(resources, stringArray)

fun Long.formEnding(resources: Resources, @ArrayRes stringArray: Int): String {
    val takeLast = if (this in 10..19) 2 else 1
    val value = this.toString().takeLast(takeLast).toInt()
    return when (value) {
        0, in 5..19 -> resources.getStringArray(stringArray)[0]
        1 -> resources.getStringArray(stringArray)[1]
        2, 3, 4 -> resources.getStringArray(stringArray)[2]
        else -> resources.getStringArray(stringArray)[0]
    }
}

package com.example.core.extensions

import android.content.res.Resources
import androidx.annotation.ArrayRes


fun Char.formEnding(resources: Resources, @ArrayRes stringArray: Int): String =
    when (this) {
        '0', '5', '6', '7', '8', '9' -> resources.getStringArray(stringArray)[0]
        '1' -> resources.getStringArray(stringArray)[1]
        '2', '3', '4' -> resources.getStringArray(stringArray)[2]
        else -> ""
    }

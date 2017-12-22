package com.apps.jean.emergencyservices.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

fun Activity.writeToSharedPreferences(block: (SharedPreferences.Editor) -> Unit) {
    val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    block(editor)
    editor.apply()
}

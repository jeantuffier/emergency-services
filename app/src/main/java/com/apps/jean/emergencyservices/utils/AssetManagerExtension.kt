package com.apps.jean.emergencyservices.utils

import android.content.res.AssetManager
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream

fun AssetManager.read(filename: String) : String {
    val bufferedInputStream = BufferedInputStream(open(filename))
    val byteArrayOutputStream = ByteArrayOutputStream()
    var result = bufferedInputStream.read()
    while (result != -1) {
        byteArrayOutputStream.write(result)
        result = bufferedInputStream.read()
    }

    return byteArrayOutputStream.toString("UTF-8")
}
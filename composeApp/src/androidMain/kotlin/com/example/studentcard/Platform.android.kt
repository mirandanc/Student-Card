package com.example.studentcard

import android.os.Build

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun ByteArray.toImageBitmap(): ImageBitmap = 
    BitmapFactory.decodeByteArray(this, 0, this.size).asImageBitmap()
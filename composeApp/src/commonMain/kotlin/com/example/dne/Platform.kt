package com.example.dne

import androidx.compose.ui.graphics.ImageBitmap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun ByteArray.toImageBitmap(): ImageBitmap
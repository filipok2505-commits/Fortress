package com.example.common.extention

class ByteArrayExtensions {
    fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }
}
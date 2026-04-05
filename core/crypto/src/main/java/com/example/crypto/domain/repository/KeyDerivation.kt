package com.example.crypto.domain.repository

interface KeyDerivation{
    fun deriveKeyFromPassword(
        password: CharArray,
        salt: ByteArray,
        iterations: Int =
    )
}
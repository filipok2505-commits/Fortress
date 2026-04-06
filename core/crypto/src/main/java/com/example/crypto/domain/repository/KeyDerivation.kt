package com.example.crypto.domain.repository

import com.example.common.constants.CryptoConstants
import javax.crypto.SecretKey

interface KeyDerivation{

    fun deriveKeyFromPassword(
        password: CharArray,
        salt: ByteArray,
        iterations: Int = CryptoConstants.ITERATIONS,
        keyLength: Int = 256
    ): SecretKey

    fun generateSalt(size: Int = CryptoConstants.SALT_SIZE): ByteArray
}
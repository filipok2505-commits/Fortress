package com.example.crypto.data.repository

import com.example.crypto.domain.repository.KeyDerivation
import java.security.SecureRandom
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class Pbkdf2KeyDerivationImpl: KeyDerivation {
    override fun deriveKeyFromPassword(
        password: CharArray,
        salt: ByteArray,
        iterations: Int,
        keyLength: Int
    ): SecretKey {

        val keySpec = PBEKeySpec(password, salt,iterations,keyLength)
        val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val secret = keyFactory.generateSecret(keySpec)
        return SecretKeySpec(secret.encoded,"AES")

    }

    override fun generateSalt(size: Int): ByteArray {
        val salt = ByteArray(size)
        SecureRandom().nextBytes(salt)
        return salt
    }
}
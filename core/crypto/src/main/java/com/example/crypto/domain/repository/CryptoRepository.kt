package com.example.crypto.domain.repository

import com.example.crypto.domain.model.EncryptedData

interface CryptoRepository {

    fun encrypt(plainText: ByteArray): EncryptedData

    fun decrypt(encryptedData: EncryptedData): ByteArray
}
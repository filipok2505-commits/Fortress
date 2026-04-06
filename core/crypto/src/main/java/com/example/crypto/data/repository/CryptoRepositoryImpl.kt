package com.example.crypto.data.repository

import com.example.crypto.domain.model.EncryptedData
import com.example.crypto.domain.repository.CryptoRepository

class CryptoRepositoryImpl: CryptoRepository {
    override fun encrypt(plainText: ByteArray): EncryptedData {
        TODO("Not yet implemented")
    }

    override fun decrypt(encryptedData: EncryptedData): ByteArray {
        TODO("Not yet implemented")
    }
}
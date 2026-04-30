package com.example.crypto.domain.usecase

import com.example.crypto.domain.model.EncryptedData
import com.example.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

class EncryptUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    operator fun invoke(plaintext: ByteArray): EncryptedData{
        return cryptoRepository.encrypt(plaintext)
    }

}
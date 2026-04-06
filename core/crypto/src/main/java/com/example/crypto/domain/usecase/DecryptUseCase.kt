package com.example.crypto.domain.usecase

import com.example.crypto.domain.model.EncryptedData
import com.example.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

class DecryptUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {

    operator fun invoke(encryptedData: EncryptedData): ByteArray {
        return cryptoRepository.decrypt(encryptedData)
    }

}
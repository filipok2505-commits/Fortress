package com.example.crypto.domain.usecase

import com.example.crypto.domain.repository.KeyManager
import javax.crypto.SecretKey
import javax.inject.Inject

class GenerateKeyUseCase @Inject constructor(
    private val keyManager: KeyManager
) {

    operator fun invoke(alias: String): SecretKey {
        return keyManager.getOrCreateKey(alias)
    }
}
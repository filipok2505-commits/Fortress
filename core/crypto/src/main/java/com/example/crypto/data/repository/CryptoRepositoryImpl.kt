package com.example.crypto.data.repository

import com.example.crypto.domain.model.EncryptedData
import com.example.crypto.domain.repository.CryptoRepository
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val keyManager: KeyManagerImpl
) : CryptoRepository {

    companion object {
        private const val ALIAS = "fortress_crypto_key"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val GCM_TAG_LENGTH = 128
    }

    override fun encrypt(plainText: ByteArray): EncryptedData {
        // 1. Получаем ключ из Keystore
        val key = keyManager.getOrCreateKey(ALIAS)

        // 2. Создаём шифр
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // 3. Получаем IV (сгенерируется автоматически)
        val iv = cipher.iv

        // 4. Шифруем
        val encryptedBytes = cipher.doFinal(plainText)

        // 5. Возвращаем результат
        return EncryptedData(encryptedBytes, iv)
    }

    override fun decrypt(encryptedData: EncryptedData): ByteArray {
        // 1. Получаем ключ из Keystore
        val key = keyManager.getOrCreateKey(ALIAS)

        // 2. Создаём шифр с параметрами IV
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(GCM_TAG_LENGTH, encryptedData.initializationVector)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)

        // 3. Расшифровываем
        return cipher.doFinal(encryptedData.encryptedBytes)
    }
}
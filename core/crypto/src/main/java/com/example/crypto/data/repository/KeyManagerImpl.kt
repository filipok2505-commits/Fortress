package com.example.crypto.data.repository

import com.example.crypto.domain.repository.KeyManager
import javax.crypto.SecretKey

class KeyManagerImpl: KeyManager {
    override fun getOrCreateKey(alias: String): SecretKey {
        TODO("Not yet implemented")
    }

    override fun deleteKey(alias: String) {
        TODO("Not yet implemented")
    }

    override fun containsKey(alias: String): Boolean {
        TODO("Not yet implemented")
    }
}
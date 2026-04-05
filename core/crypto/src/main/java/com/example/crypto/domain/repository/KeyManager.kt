package com.example.crypto.domain.repository

import javax.crypto.SecretKey

interface KeyManager {
    fun getOrCreateKey(alias: String): SecretKey
    fun deleteKey(alias: String)
    fun containsKey(alias: String): Boolean
}
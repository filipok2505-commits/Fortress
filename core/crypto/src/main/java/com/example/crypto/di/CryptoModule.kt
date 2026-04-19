package com.example.crypto.di

import com.example.crypto.data.repository.KeyManagerImpl
import com.example.crypto.data.repository.CryptoRepositoryImpl
import com.example.crypto.data.repository.Pbkdf2KeyDerivationImpl
import com.example.crypto.domain.repository.CryptoRepository
import com.example.crypto.domain.repository.KeyDerivation
import com.example.crypto.domain.repository.KeyManager
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
class CryptoModule {

    @Provides
    @Singleton
    fun provideKeyManager(): KeyManager = KeyManagerImpl()

    @Provides
    @Singleton
    fun provideKeyDerivation(): KeyDerivation = Pbkdf2KeyDerivationImpl()

    @Provides
    @Singleton
    fun provideCryptoRepository(keyManager: KeyManagerImpl): CryptoRepository {
        return CryptoRepositoryImpl(keyManager)
    }
}
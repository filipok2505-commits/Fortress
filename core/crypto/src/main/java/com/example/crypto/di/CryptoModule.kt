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
    fun provideCryptoRepository(): CryptoRepository {
        return CryptoRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideKeyManager(): KeyManager {
        return KeyManagerImpl()
    }

    @Provides
    @Singleton
    fun provideKeyDerivation(): KeyDerivation {
        return Pbkdf2KeyDerivationImpl()
    }
}
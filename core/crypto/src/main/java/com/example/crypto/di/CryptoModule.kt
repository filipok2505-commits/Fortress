package com.example.crypto.di

import com.example.crypto.data.repository.KeyManagerImpl
import com.example.crypto.data.repository.CryptoRepositoryImpl
import com.example.crypto.data.repository.Pbkdf2KeyDerivationImpl
import com.example.crypto.domain.repository.CryptoRepository
import com.example.crypto.domain.repository.KeyDerivation
import com.example.crypto.domain.repository.KeyManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoModule {

    @Binds
    @Singleton
    abstract fun provideKeyManager(impl: KeyManagerImpl): KeyManager

    @Binds
    @Singleton
    abstract fun provideKeyDerivation(impl: Pbkdf2KeyDerivationImpl): KeyDerivation

    @Binds
    @Singleton
    abstract fun provideCryptoRepository(
        impl: CryptoRepositoryImpl
    ): CryptoRepository
}
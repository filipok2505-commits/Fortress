package com.example.vault.di

import com.example.vault.data.common.DefaultDispatcherProvider
import com.example.vault.data.common.DispatcherProvider
import com.example.vault.data.repository.VaultRepositoryImpl
import com.example.vault.domain.repository.VaultRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class VaultModule {

    @Binds
    @Singleton
    abstract fun bindVaultRepository(
        impl: VaultRepositoryImpl
    ): VaultRepository


    companion object {
        @Provides
        @Singleton
        fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
    }
}
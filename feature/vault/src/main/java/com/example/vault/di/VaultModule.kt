package com.example.vault.di

import com.example.vault.data.repository.VaultRepositoryImpl
import com.example.vault.domain.repository.VaultRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VaultModule {

    @Binds
    @Singleton
    abstract fun bindVaultRepository(
        impl: VaultRepositoryImpl
    ): VaultRepository
}
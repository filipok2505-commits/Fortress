package com.example.vault.di

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VaultModule {
    @Binds
    @Singleton
    abstract fun bindVault()
}
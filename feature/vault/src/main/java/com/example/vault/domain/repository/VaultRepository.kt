package com.example.vault.domain.repository

import com.example.vault.domain.model.Vault
import kotlinx.coroutines.flow.Flow

interface VaultRepository {

    fun getVaults(): Flow<List<Vault>>

    suspend fun getVaultById(id: Long): Vault?

    suspend fun insertVault(vault: Vault): Long

    suspend fun updateVault(vault: Vault)

    suspend fun deleteVault(vault: Vault)
}
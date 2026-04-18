package com.example.vault.data.repository

import com.example.vault.domain.model.Vault
import com.example.vault.domain.repository.VaultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VaultRepositoryImpl @Inject constructor(

): VaultRepository {
    override fun getVaults(): Flow<List<Vault>> {
        TODO("Not yet implemented")
    }

    override suspend fun getVaultById(id: Long): Vault? {
        TODO("Not yet implemented")
    }

    override suspend fun insertVault(vault: Vault): Long {
        TODO("Not yet implemented")
    }

    override suspend fun updateVault(vault: Vault) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVault(vault: Vault) {
        TODO("Not yet implemented")
    }
}
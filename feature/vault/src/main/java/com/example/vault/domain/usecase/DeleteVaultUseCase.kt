package com.example.vault.domain.usecase

import com.example.vault.domain.model.Vault
import com.example.vault.domain.repository.VaultRepository
import javax.inject.Inject

class DeleteVaultUseCase @Inject constructor(
    private val repository: VaultRepository
) {
    suspend operator fun invoke(id: Long) {
        return repository.deleteVault(id)
    }
}
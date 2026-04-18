package com.example.vault.domain.usecase

import com.example.vault.domain.model.Vault
import com.example.vault.domain.repository.VaultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVaultsUseCase @Inject constructor(
    private val repository: VaultRepository
) {
    operator fun invoke(): Flow<List<Vault>> {
        return repository.getVaults()
    }
}
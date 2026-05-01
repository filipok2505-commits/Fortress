package com.example.vault.domain.usecase

import com.example.vault.domain.model.Vault
import com.example.vault.domain.repository.VaultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVaultByIdUseCase @Inject constructor(
    private val repository: VaultRepository
) {
    operator fun invoke(id: Long): Flow<Vault?> = flow {
        emit(repository.getVaultById(id))
    }
}
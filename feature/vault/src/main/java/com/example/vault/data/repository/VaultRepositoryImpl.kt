package com.example.vault.data.repository

import com.example.crypto.domain.model.EncryptedData
import com.example.crypto.domain.repository.CryptoRepository
import com.example.database.data.local.dao.VaultDao
import com.example.database.data.local.entity.VaultEntity
import com.example.vault.data.common.DispatcherProvider
import com.example.vault.data.mapper.fromDomain
import com.example.vault.data.mapper.toDomain
import com.example.vault.domain.model.Vault
import com.example.vault.domain.repository.VaultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VaultRepositoryImpl @Inject constructor(
    private val vaultDao: VaultDao,
    private val dispatchers: DispatcherProvider,
    private val cryptoRepository: CryptoRepository
) : VaultRepository {

    override fun getVaults(): Flow<List<Vault>> {
        return vaultDao.getAllVaults()
            .map { entities ->
                entities.map { entity ->
                    val decryptedBytes = cryptoRepository.decrypt(
                        EncryptedData(
                            encryptedBytes = entity.encryptedPassword,
                            initializationVector = entity.initVector
                        )
                    )
                    val decryptedPassword = String(decryptedBytes)
                    entity.toDomain(decryptedPassword)
                }
            }
    }

    override suspend fun getVaultById(id: Long): Vault? = withContext(dispatchers.io) {
        val entity = vaultDao.getVaultById(id) ?: return@withContext null

        val decryptedBytes = cryptoRepository.decrypt(
            EncryptedData(
                encryptedBytes = entity.encryptedPassword,
                initializationVector = entity.initVector
            )
        )
        val decryptedPassword = String(decryptedBytes)

        return@withContext entity.toDomain(decryptedPassword)
    }

    override suspend fun insertVault(vault: Vault): Long = withContext(dispatchers.io) {

        val encrypted = cryptoRepository.encrypt(vault.password.toByteArray())
        val entity = vault.fromDomain(
            encryptedPassword = encrypted.encryptedBytes,
            initVector = encrypted.initializationVector
        )
        vaultDao.insert(entity)
    }

    override suspend fun updateVault(vault: Vault) = withContext(dispatchers.io) {
        val encrypted = cryptoRepository.encrypt(vault.password.toByteArray())
        val entity = vault.fromDomain(
            encryptedPassword = encrypted.encryptedBytes,
            initVector = encrypted.initializationVector
        )
        vaultDao.update(entity)
    }

    override suspend fun deleteVault(id: Long) = withContext(dispatchers.io) {
        vaultDao.deleteById(id)
    }


}
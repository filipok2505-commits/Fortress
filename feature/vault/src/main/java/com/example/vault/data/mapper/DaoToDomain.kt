package com.example.vault.data.mapper

import com.example.database.data.local.entity.VaultEntity
import com.example.vault.domain.model.Vault

fun VaultEntity.toDomain(decryptedPassword: String): Vault {
    return Vault(
        id = this.id,
        appTitle = this.appTitle,
        url = this.url,
        userName = this.userName,
        password = decryptedPassword,
        notes = this.notes,
        createdAtMillis = this.createdAt
    )
}

fun Vault.fromDomain(
    encryptedPassword: ByteArray, initVector: ByteArray
): VaultEntity {
    return VaultEntity(
        id = this.id,
        appTitle = this.appTitle,
        url = this.url,
        userName = this.userName,
        encryptedPassword = encryptedPassword,
        initVector = initVector,
        notes = this.notes,
        createdAt = this.createdAtMillis
    )
}
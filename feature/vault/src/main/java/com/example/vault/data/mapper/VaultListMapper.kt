package com.example.vault.data.mapper

import com.example.vault.domain.model.Vault
import com.example.vault.presentation.vaultList.model.PasswordCardUi

fun Vault.toUi(): PasswordCardUi {
    return PasswordCardUi(
        id = id,
        title = appTitle,
        subtitle = userName,
        password = password,
        isPasswordVisible = false
    )
}

fun PasswordCardUi.toVault(): Vault {
    return Vault(
        id = id,
        appTitle = title,
        userName = subtitle,
        password = password,
        url = "",           
        notes = "",
        createdAtMillis = System.currentTimeMillis()
    )
}
package com.example.vault.presentation.vaultList.model

import androidx.compose.runtime.Immutable

@Immutable
data class PasswordCardUi(
    val id: Long,
    val title: String,
    val subtitle: String,
    val password: String,
    val isPasswordVisible: Boolean = false
)
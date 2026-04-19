package com.example.vault.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Vault(
    val id: Long = 0,
    val appTitle: String,
    val url: String,
    val userName: String,
    val password: String,
    val notes: String,
    val createdAtMillis: Long,
)

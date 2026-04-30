package com.example.vault.presentation.vaultList

import com.example.vault.domain.model.Vault

sealed class VaultEvent {
    object LoadVaults : VaultEvent()
    data class AddEntry(
        val appTitle: String,
        val url: String = "",
        val userName: String,
        val password: String,
        val notes: String = ""
    ) : VaultEvent()

    data class UpdateEntry(
        val entry: Vault
    ) : VaultEvent()

    data class DeleteEntry(
        val id: Long
    ) : VaultEvent()

    data class SearchEntries(
        val query: String
    ) : VaultEvent()

    data class SortEntries(
        val sortType: SortType
    ) : VaultEvent()

    object ClearSearch : VaultEvent()

    enum class SortType {
        BY_TITLE_ASC, BY_TITLE_DESC, BY_DATE_ASC, BY_DATE_DESC
    }
}
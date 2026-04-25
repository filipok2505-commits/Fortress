package com.example.vault.presentation.vaultList

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vault.domain.model.Vault
import com.example.vault.domain.usecase.CreateVaultUseCase
import com.example.vault.domain.usecase.DeleteVaultUseCase
import com.example.vault.domain.usecase.GetVaultByIdUseCase
import com.example.vault.domain.usecase.GetVaultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        BY_TITLE_ASC,
        BY_TITLE_DESC,
        BY_DATE_ASC,
        BY_DATE_DESC
    }
}


@Immutable
data class VaultListUiState(
    val vaults: List<Vault> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)

@HiltViewModel
class VaultListViewModel @Inject constructor(
    private val createVaultUseCase: CreateVaultUseCase,
    private val updateVaultsUseCase: GetVaultsUseCase,
    private val getVaultsUseCase: GetVaultsUseCase,
    private val getVaultByIdUseCase: GetVaultByIdUseCase,
    private val deleteVaultUseCase: DeleteVaultUseCase,

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(VaultListUiState())
    val uiState: StateFlow<VaultListUiState> = _uiState.asStateFlow()

    fun onEvent(event: VaultEvent) {
        when (event) {
            VaultEvent.LoadVaults -> loadVaults()
            is VaultEvent.AddEntry -> TODO()
            VaultEvent.ClearSearch -> TODO()
            is VaultEvent.DeleteEntry -> TODO()
            is VaultEvent.SearchEntries -> TODO()
            is VaultEvent.SortEntries -> TODO()
            is VaultEvent.UpdateEntry -> TODO()
        }
    }

    private fun loadVaults() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getVaultsUseCase.invoke().collect { vaults ->
                _uiState.update { currentState ->
                    currentState.copy(
                        vaults = vaults,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun deleteVault(vault: Vault) {
        viewModelScope.launch {
            deleteVaultUseCase(vault)
        }
    }

    private fun createVault(vault: Vault) {
        viewModelScope.launch {
            createVaultUseCase(vault)
        }
    }


}
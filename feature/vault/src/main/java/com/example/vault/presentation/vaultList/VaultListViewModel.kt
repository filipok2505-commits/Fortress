package com.example.vault.presentation.vaultList

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vault.domain.model.Vault
import com.example.vault.domain.usecase.CreateVaultUseCase
import com.example.vault.domain.usecase.DeleteVaultUseCase
import com.example.vault.domain.usecase.GetVaultByIdUseCase
import com.example.vault.domain.usecase.GetVaultsUseCase
import com.example.vault.domain.usecase.UpdateVaults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class VaultListUiState(
    val vaults: List<Vault> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val selectedCategory: String = "All Passwords"
)

@HiltViewModel
class VaultListViewModel @Inject constructor(
    private val createVaultUseCase: CreateVaultUseCase,
    private val updateVaultsUseCase: UpdateVaults,
    private val getVaultsUseCase: GetVaultsUseCase,
    private val getVaultByIdUseCase: GetVaultByIdUseCase,
    private val deleteVaultUseCase: DeleteVaultUseCase,

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(VaultListUiState())
    val uiState: StateFlow<VaultListUiState> = _uiState.asStateFlow()

    init {
        loadVaults()
    }

    fun onEvent(event: VaultEvent) {
        when (event) {
            VaultEvent.LoadVaults -> loadVaults()
            is VaultEvent.AddEntry -> {
                val vault = Vault(
                    appTitle = event.appTitle,
                    url = event.url,
                    userName = event.userName,
                    password = event.password,
                    notes = event.notes,
                    createdAtMillis = System.currentTimeMillis()
                )
            }

            VaultEvent.ClearSearch -> {
                _uiState.update { it.copy(searchQuery = "") }
            }

            is VaultEvent.DeleteEntry -> deleteVault(event.id)
            is VaultEvent.SearchEntries -> search(event.query)
            is VaultEvent.SortEntries -> sort(event.sortType)
            is VaultEvent.UpdateEntry -> updateVaults(event.entry)
        }
    }

    private fun loadVaults() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getVaultsUseCase.invoke().collect { vaults ->
                _uiState.update { currentState ->
                    currentState.copy(
                        vaults = vaults, isLoading = false
                    )
                }
            }
        }
    }

    private fun deleteVault(id: Long) {
        viewModelScope.launch {
            deleteVaultUseCase(id)
        }
    }

    private fun createVault(vault: Vault) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            createVaultUseCase(vault)
            _uiState.update { it.copy(isLoading = false) }

        }
    }

    private fun updateVaults(entry: Vault) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            updateVaultsUseCase(entry)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun search(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    private fun sort(sortType: VaultEvent.SortType) {

    }
}
package com.example.vault.presentation.vaultList

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vault.data.mapper.toUi
import com.example.vault.domain.model.Vault
import com.example.vault.domain.usecase.CreateVaultUseCase
import com.example.vault.domain.usecase.DeleteVaultUseCase
import com.example.vault.domain.usecase.GetVaultByIdUseCase
import com.example.vault.domain.usecase.GetVaultsUseCase
import com.example.vault.domain.usecase.UpdateVaults
import com.example.vault.presentation.vaultList.model.PasswordCardUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class VaultListUiState(
    val vaults: List<PasswordCardUi> = emptyList(),
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

    private var allVaults: List<PasswordCardUi> = emptyList()

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

                createVault(vault)
            }

            VaultEvent.ClearSearch -> {
                _uiState.update { it.copy(searchQuery = "") }
            }

            is VaultEvent.DeleteEntry -> deleteVault(event.id)
            is VaultEvent.SearchEntries -> search(event.query)
            is VaultEvent.SortEntries -> sort(event.sortType)
            is VaultEvent.UpdateEntry -> updateVaults(event.entry)
            is VaultEvent.TogglePasswordVisibility -> togglePasswordVisibility(event.id)
        }
    }


    private fun loadVaults() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getVaultsUseCase.invoke().collect { vaults ->
                val uiVaults = vaults.map { it.toUi() }
                allVaults = uiVaults

                _uiState.update { currentState ->
                    currentState.copy(
                        vaults = filterVaults(uiVaults, currentState.searchQuery),
                        isLoading = false
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
            createVaultUseCase(vault)
            loadVaults()
        }
    }

    private fun updateVaults(entry: Vault) {
        viewModelScope.launch {
            updateVaultsUseCase(entry)
        }
    }

    private fun search(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                vaults = filterVaults(allVaults, query)
            )
        }
    }

    private fun filterVaults(vaults: List<PasswordCardUi>, query: String): List<PasswordCardUi> {
        if (query.isBlank()) return vaults
        return vaults.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.subtitle.contains(query, ignoreCase = true)
        }
    }

    private fun sort(sortType: VaultEvent.SortType) {
        _uiState.update { state ->
            val sorted = when (sortType) {
                VaultEvent.SortType.BY_TITLE_ASC -> state.vaults.sortedBy { it.title }
                VaultEvent.SortType.BY_TITLE_DESC -> state.vaults.sortedByDescending { it.title }
                VaultEvent.SortType.BY_DATE_ASC -> state.vaults
                VaultEvent.SortType.BY_DATE_DESC -> state.vaults
            }
            state.copy(vaults = sorted)
        }
    }
    private fun togglePasswordVisibility(id: Long) {
        _uiState.update { state ->
            state.copy(
                vaults = state.vaults.map { item ->
                    if (item.id == id) {
                        item.copy(
                            isPasswordVisible = !item.isPasswordVisible
                        )
                    } else item
                }
            )
        }
    }

}
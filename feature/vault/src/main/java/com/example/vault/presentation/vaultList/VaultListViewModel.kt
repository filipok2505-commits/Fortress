package com.example.vault.presentation.vaultList

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.vault.domain.model.Vault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@Immutable
data class VaultListUiState(
    val vaults: List<Vault> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class VaultListViewModel @Inject constructor(
): ViewModel() {
    private val _uiState = MutableStateFlow(VaultListUiState())
    val uiState: StateFlow<VaultListUiState> = _uiState.asStateFlow()

}
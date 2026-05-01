package com.example.fortress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fortress.ui.theme.FortressTheme
import com.example.vault.presentation.splashScreen.SplashScreen
import com.example.vault.presentation.vaultList.VaultEvent

import com.example.vault.presentation.vaultList.VaultListScreen
import com.example.vault.presentation.vaultList.VaultListUiState
import com.example.vault.presentation.vaultList.VaultListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplash by remember { mutableStateOf(true) }
            
            val viewModel: VaultListViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            FortressTheme {
                if (showSplash) {
                    SplashScreen(
                        viewModel = hiltViewModel(),
                        onTimeout = { showSplash = false }
                    )
                } else {
                    VaultListScreen(
                        state = state,  
                        onEvent = viewModel::onEvent,
                        onAddClick = { },
                        onItemClick = { /* TODO */ },
                        onEyeClick = { /* TODO */ },
                        onCopyClick = { /* TODO */ },
                        onBottomNavClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}
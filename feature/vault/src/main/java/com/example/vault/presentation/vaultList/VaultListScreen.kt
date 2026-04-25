package com.example.vault.presentation.vaultList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.TextSecondaryColor
import com.fortress.ui.theme.PassVaultTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vault.domain.model.Vault

@Composable
fun VaultListScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.padding(16.dp))

        GreetingHeader()
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewVaultScreen() {

}

@Composable
fun GreetingHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {

            Text(
                text = stringResource(R.string.Welcome),
                style = PassVaultTypography.displayMedium,
                color = TextSecondaryColor
            )

            Text(
                text = /*stringResource(R.string.Helo)*/("Hello, "),
                style = PassVaultTypography.displayLarge
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier.size(44.dp)) {
            Icon(
                painter = painterResource(R.drawable.outline_circle_24),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                painter = painterResource(R.drawable.filled_circle),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(12.dp),
                tint = Color.Green

            )
        }
    }
}

@Composable
@Preview
fun VaultPreviewGpt(){
    VaultListRoute()
}

@Composable
fun VaultListRoute(
    viewModel: VaultListViewModel = hiltViewModel(),
    onAddClick: () -> Unit = {},
    onItemClick: (Vault) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    VaultListScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onAddClick = onAddClick,
        onItemClick = onItemClick
    )
}

@Composable
fun VaultListScreen(
    state: VaultListUiState,
    onEvent: (VaultEvent) -> Unit,
    onAddClick: () -> Unit,
    onItemClick: (Vault) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF6F7FB),
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Color(0xFF5B4CF0)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
            }
        },
        bottomBar = { VaultBottomBar() }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(20.dp))

            Header()

            Spacer(Modifier.height(20.dp))

            SearchBar(
                query = state.searchQuery,
                onQueryChange = {
                    onEvent(VaultEvent.SearchEntries(it))
                }
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "RECENT ITEMS",
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF7D8AA5),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(
                        items = state.vaults,
                        key = { it.id }
                    ) { vault ->

                        VaultItem(
                            vault = vault,
                            onClick = { onItemClick(vault) },
                            onDelete = {
                                onEvent(VaultEvent.DeleteEntry(vault.id))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = "Welcome back,",
                color = Color(0xFF7D8AA5)
            )

            Text(
                text = "Hello, BlogTriggers",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3E8FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Person, null)
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search vault...") },
            leadingIcon = {
                Icon(Icons.Outlined.Search, null)
            },
            shape = RoundedCornerShape(24.dp),
            singleLine = true
        )

        Spacer(Modifier.width(12.dp))

        IconButton(onClick = {}) {
            Icon(painter = painterResource(R.drawable.tune_google), null)
        }
    }
}

@Composable
private fun VaultItem(
    vault: Vault,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF1F4FA)),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(R.drawable.shield_google), null)
            }

            Spacer(Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vault.appTitle,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = vault.userName,
                    color = Color(0xFF7D8AA5)
                )
            }

            IconButton(onClick = {}) {
                Icon(painter = painterResource( R.drawable.visibility_google), null)
            }

            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.content_copy_google), null)
            }
        }
    }
}

@Composable
private fun VaultBottomBar() {
    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon( painter = painterResource(R.drawable.shield_google), null) },
            label = { Text("Vault") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(  painter = painterResource(R.drawable.shield_google), null) },
            label = { Text("Generator") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon( painter = painterResource(R.drawable.security_google), null) },
            label = { Text("Security") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Settings, null) },
            label = { Text("Settings") }
        )
    }
}

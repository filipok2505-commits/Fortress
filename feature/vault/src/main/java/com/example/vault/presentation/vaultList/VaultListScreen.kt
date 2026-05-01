package com.example.vault.presentation.vaultList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.TextSecondaryColor
import com.fortress.ui.theme.PassVaultTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import com.example.ui.theme.Border
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.Success
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextPrimaryColor
import com.example.vault.presentation.components.BottomNavigationBar
import com.example.vault.presentation.components.PasswordCard
import com.example.vault.presentation.components.SortBlocks
import com.example.vault.presentation.vaultList.model.PasswordCardUi

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun VaultListScreen(
    state: VaultListUiState,
    onEvent: (VaultEvent) -> Unit,
    onAddClick: () -> Unit,
    onItemClick: (PasswordCardUi) -> Unit,
    onEyeClick: (PasswordCardUi) -> Unit,
    onCopyClick: (PasswordCardUi) -> Unit,
    onBottomNavClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = SurfaceColor,
        topBar = { TopBarVault() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = PrimaryColor
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null, tint = SurfaceColor)
            }
        },
        bottomBar = {
            BottomNavigationBar(
                selectedId = "vault",
                onItemClick = onBottomNavClick
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = state.searchQuery,
                onQueryChange = { onEvent(VaultEvent.SearchEntries(it)) }
            )
            CategoriesRow(
                selectedCategory = state.selectedCategory,
                onCategorySelected = { category ->
                    onEvent(VaultEvent.SortEntries(VaultEvent.SortType.BY_TITLE_ASC))
                }
            )
            Text(
                modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 16.dp),
                text = stringResource(R.string.Recent),
                style = PassVaultTypography.titleSmall,
                color = TextSecondaryColor
            )
            if (state.vaults.isNotEmpty()) {
                VaultItemList(
                    vaults = state.vaults,
                    onItemClick = onItemClick,
                    onEyeClick = onEyeClick,
                    onCopyClick = onCopyClick
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Password") },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username / Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (title.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                            onEvent(VaultEvent.AddEntry(
                                appTitle = title,
                                userName = username,
                                password = password
                            ))
                            showDialog = false
                            title = ""
                            username = ""
                            password = ""
                        }
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun TopBarVault() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.Welcome),
                    style = PassVaultTypography.bodyMedium,
                    color = TextSecondaryColor
                )
                Text(
                    text = stringResource(R.string.Hello) + (", BlogTriggers"),
                    style = PassVaultTypography.headlineLarge,
                    color = TextPrimaryColor
                )
            }
            Box(
                modifier = Modifier.size(48.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp, color = Border, shape = CircleShape
                        )
                        .padding(1.dp)
                        .clip(CircleShape)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-1).dp, y = (-1).dp)
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(SurfaceColor)
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Success)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .align(Alignment.TopCenter)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        "Search vault...",
                        color = TextSecondaryColor,
                        style = PassVaultTypography.bodyMedium
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = TextSecondaryColor
                    )
                },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Border,
                    unfocusedBorderColor = Border,
                    focusedTextColor = TextPrimaryColor,
                    unfocusedTextColor = TextPrimaryColor,
                    cursorColor = TextPrimaryColor
                )
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp, color = Border, shape = CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.tune_google),
                    contentDescription = "Filter",
                    tint = TextSecondaryColor
                )
            }
        }
    }
}

@Composable
fun CategoriesRow(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("All Passwords", "Work", "Finance", "Social")
    Box(
        modifier = Modifier.height(66.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                SortBlocks(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}

@Composable
private fun VaultItemList(
    vaults: List<PasswordCardUi>,
    onItemClick: (PasswordCardUi) -> Unit,
    onEyeClick: (PasswordCardUi) -> Unit,
    onCopyClick: (PasswordCardUi) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(540.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = vaults,
            key = { it.id }
        ) { item ->
            PasswordCard(
                item = item,
                onItemClick = { onItemClick(item) },
                onEyeClick = { onEyeClick(item) },
                onCopyClick = { onCopyClick(item) }
            )
        }
    }
}

@Composable
fun VaultBottomBar() {
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
            icon = { Icon(painter = painterResource(R.drawable.shield_google), null) },
            label = { Text("Vault") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painter = painterResource(R.drawable.key_vertical_google), null) },
            label = { Text("Generator") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painter = painterResource(R.drawable.security_google), null) },
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

@Preview(showBackground = true, name = "With Items")
@Composable
private fun PreviewVaultListScreen_WithItems() {
    val fakeVaults = listOf(
        PasswordCardUi(
            id = 1,
            title = "Google",
            subtitle = "blogtriggers@gmail.com",
            password = "***",
            isPasswordVisible = false
        ),
        PasswordCardUi(
            id = 2,
            title = "GitHub",
            subtitle = "origami@gmail.com",
            password = "***",
            isPasswordVisible = false
        ),
        PasswordCardUi(
            id = 3,
            title = "Netflix",
            subtitle = "user@netflix.com",
            password = "***",
            isPasswordVisible = false
        )
    )

    val fakeState = VaultListUiState(
        vaults = fakeVaults,
        isLoading = false,
        searchQuery = "",
        selectedCategory = "All Passwords"
    )

    VaultListScreen(
        state = fakeState,
        onEvent = {},
        onAddClick = {},
        onItemClick = {},
        onEyeClick = {},
        onCopyClick = {},
        onBottomNavClick = {}
    )
}
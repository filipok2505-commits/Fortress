package com.example.vault.presentation.vaultList

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.theme.Border
import com.example.ui.theme.Success
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextPrimaryColor
import com.example.vault.domain.model.Vault
import com.example.vault.presentation.components.SortBlocks


@Composable
fun VaultListScreen(
    state: VaultListUiState,
    onEvent: (VaultEvent) -> Unit,
    onAddClick: () -> Unit,
    onItemClick: (Vault) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF6F7FB),
        topBar = { TopBarVault() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick, containerColor = Color(0xFF5B4CF0)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
            }
        },
        bottomBar = { VaultBottomBar() },
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


            


        }
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
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        letterSpacing = 0.sp
                    ),
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
            .height(70.dp), contentAlignment = Alignment.TopStart

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
                onClick = {}, modifier = Modifier
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
     modifier = Modifier.height(34.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            categories.forEach { category ->
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
private fun VaultBottomBar() {
    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, null) },
            label = { Text("Home") })

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painter = painterResource(R.drawable.shield_google), null) },
            label = { Text("Vault") })

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painter = painterResource(R.drawable.key_vertical_google), null) },
            label = { Text("Generator") })

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(painter = painterResource(R.drawable.security_google), null) },
            label = { Text("Security") })

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Settings, null) },
            label = { Text("Settings") })
    }
}

@Preview(showBackground = true, name = "Empty State")
@Composable
private fun PreviewVaultListScreen_Empty() {
    val fakeState = VaultListUiState(
        vaults = emptyList(),
        isLoading = false,
        searchQuery = "",
        selectedCategory = "All Passwords"
    )

    VaultListScreen(
        state = fakeState,
        onEvent = {},
        onAddClick = {},
        onItemClick = {}
    )
}


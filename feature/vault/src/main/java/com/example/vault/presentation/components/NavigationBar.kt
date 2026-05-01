package com.example.vault.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextSecondaryColor
import com.fortress.ui.theme.PassVaultTypography

@Immutable
data class BottomBarItemModel(
    val id: String,
    val icon: Painter,
    val label: String
)

@Composable
fun BottomNavigationBar(
    selectedId: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomBarItemModel("home", painterResource(R.drawable.home_figma), "Home"),
        BottomBarItemModel("vault", painterResource(R.drawable.shield_google), "Vault"),
        BottomBarItemModel("gen", painterResource(R.drawable.key_figma), "Generator"),
        BottomBarItemModel("sec", painterResource(R.drawable.security_google), "Security"),
        BottomBarItemModel("settings", painterResource(R.drawable.setting_figma), "Settings")
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .padding(horizontal = 24.dp)
            .height(71.dp)
            .shadow(12.dp, RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp))
            .background(SurfaceColor)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomBarItem(
                    item = item,
                    isSelected = item.id == selectedId,
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    item: BottomBarItemModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) PrimaryColor else TextSecondaryColor,
        label = "bottomBarColor"
    )

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = item.icon,
            contentDescription = item.label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.label,
            style = PassVaultTypography.bodySmall,
            color = color
        )
    }
}

@Preview(showBackground = true, name = "Bottom Navigation Bar")
@Composable
private fun PreviewBottomNavigationBar() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceColor),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar(
                selectedId = "vault",
                onItemClick = { /* навигация */ }
            )
        }
    }
}

@Preview(showBackground = true, name = "Bottom Nav - Home Selected")
@Composable
private fun PreviewBottomNavigationBarHome() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceColor),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar(
                selectedId = "home",
                onItemClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Bottom Nav - Settings Selected")
@Composable
private fun PreviewBottomNavigationBarSettings() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceColor),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar(
                selectedId = "settings",
                onItemClick = {}
            )
        }
    }
}
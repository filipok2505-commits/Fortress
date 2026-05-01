package com.example.vault.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.Danger
import com.example.ui.theme.PassVaultElevation
import com.example.ui.theme.PassVaultShapes
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextPrimaryColor
import com.example.ui.theme.TextSecondaryColor
import com.example.ui.theme.cardShadow
import com.example.vault.presentation.vaultList.model.PasswordCardUi
import com.fortress.ui.theme.PassVaultTypography

@Composable
fun PasswordCard(
    item: PasswordCardUi,
    onItemClick: () -> Unit,
    onEyeClick: () -> Unit,
    onCopyClick: () -> Unit,

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .clickable(onClick = onItemClick)
            .cardShadow()
            .padding(horizontal = 24.dp),
        shape = PassVaultShapes.card,
        elevation = CardDefaults.cardElevation(PassVaultElevation.card),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(PassVaultShapes.button)
                    .background(TextPrimaryColor), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.user_around),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Danger
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .width(160.dp)
                    .height(44.dp)
            ) {
                Text(
                    text = item.title, style = PassVaultTypography.titleMedium, color = TextPrimaryColor
                )
                Text(
                    text = item.subtitle,
                    style = PassVaultTypography.bodyMedium,
                    color = TextSecondaryColor
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .width(68.dp)
                    .height(33.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    IconButton(
                        onClick = onEyeClick, modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = if (item.isPasswordVisible) {
                                painterResource(R.drawable.visibility_google)
                            } else {
                                painterResource(R.drawable.visibility_off)
                            },
                            contentDescription = if (item.isPasswordVisible) "Hide password" else "Show password",
                            tint = TextSecondaryColor
                        )
                    }

                    IconButton(
                        onClick = onCopyClick, modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.click_copy),
                            contentDescription = "Copy",
                            tint = TextSecondaryColor
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Password Card - Hidden")
@Composable
private fun PreviewPasswordCard_Hidden() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PasswordCard(
                item = PasswordCardUi(
                    id = 1,
                    title = "Google",
                    subtitle = "blogtriggers@gmail.com",
                    password = "mySecretPassword123",
                    isPasswordVisible = false
                ),
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Password Card - Visible")
@Composable
private fun PreviewPasswordCard_Visible() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PasswordCard(
                item = PasswordCardUi(
                    id = 1,
                    title = "Google",
                    subtitle = "blogtriggers@gmail.com",
                    password = "mySecretPassword123",
                    isPasswordVisible = true
                ),
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Password Card - Multiple")
@Composable
private fun PreviewPasswordCard_Multiple() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PasswordCard(
                item = PasswordCardUi(
                    id = 1,
                    title = "Google",
                    subtitle = "blogtriggers@gmail.com",
                    password = "pass123",
                    isPasswordVisible = false
                ),
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {}
            )
            PasswordCard(
                item = PasswordCardUi(
                    id = 2,
                    title = "GitHub",
                    subtitle = "blogtriggers",
                    password = "github_pass",
                    isPasswordVisible = true
                ),
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {}
            )
            PasswordCard(
                item = PasswordCardUi(
                    id = 3,
                    title = "Netflix",
                    subtitle = "user@netflix.com",
                    password = "netflix_pass",
                    isPasswordVisible = false
                ),
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {}
            )
        }
    }
}
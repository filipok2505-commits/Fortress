package com.example.vault.presentation.components

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
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextSecondaryColor

@Composable
fun PasswordCard(
    title: String,
    emailOrUsername: String,
    onItemClick: () -> Unit,
    onEyeClick: () -> Unit,
    onCopyClick: () -> Unit,
    isPasswordVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(82.dp)
            .clickable(onClick = onItemClick)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
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
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1A1A1A)
                )
                Text(
                    text = emailOrUsername,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF8E8E93)
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
                            painter = if (isPasswordVisible) {
                                painterResource(R.drawable.visibility_google)
                            } else {
                                painterResource(R.drawable.visibility_off)
                            },
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
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

@Preview(showBackground = true, name = "Password Card")
@Composable
private fun PreviewPasswordCard() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PasswordCard(
                title = "Google",
                emailOrUsername = "blogtriggers@gmail.com",
                onItemClick = {},
                onEyeClick = {},
                onCopyClick = {},
                isPasswordVisible = false,
                modifier = Modifier.padding()
            )
        }
    }
}
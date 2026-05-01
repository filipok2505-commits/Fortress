package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Тень из Figma:
 * X: 0, Y: 4, Blur: 16, Spread: 0, Color: #000000 4%
 */
fun Modifier.cardShadow(): Modifier = this.shadow(
    elevation = 4.dp,
    shape = RoundedCornerShape(16.dp),
    clip = false,
    spotColor = Color(0x0A000000),
    ambientColor = Color(0x0A000000)
)
package com.example.vault.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.R
import com.example.ui.theme.Border
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.SurfaceColor
import com.example.ui.theme.TextSecondaryColor


@Composable
fun SortBlocks(
    text: String, isSelected: Boolean, onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.height(34.dp),
        shape = RoundedCornerShape(64.dp),
        color = if (isSelected) PrimaryColor else SurfaceColor,
        border = if (!isSelected) BorderStroke(1.dp, Border) else null,
        onClick = onClick
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
            color = if (isSelected) SurfaceColor else TextSecondaryColor,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.sp
            )
        )
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesRow() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SortBlocks(text = "All Passwords", isSelected = true) {}
        SortBlocks(text = "Work", isSelected = false) {}
        SortBlocks(text = "Finance", isSelected = false) {}
        SortBlocks(text = "Social", isSelected = false) {}
    }
}
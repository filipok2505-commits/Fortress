package com.example.vault.presentation.presentation.splashScreen


import android.widget.ProgressBar
import android.window.SplashScreen
import androidx.compose.foundation.Image
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.TextSecondaryColor
import com.fortress.ui.theme.PassVaultTypography


@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier.size(128.dp)) {
            Image(
                painter = painterResource(R.drawable.shield),
                contentDescription = "Logo",
                modifier = Modifier.align(Alignment.Center)
            )
            Image(
                painter = painterResource(R.drawable.lock_butom),
                contentDescription = "Security",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }

        Text(
            text = stringResource(R.string.Fortress),
            style = PassVaultTypography.displayLarge
        )

        Text(
            text = stringResource(R.string.tagline),
            style = PassVaultTypography.displayMedium,
            color = TextSecondaryColor
        )

        Spacer(modifier = Modifier.weight(1f))

        LinearProgressIndicator(
            progress = 0.3f,
            color = PrimaryColor,
            modifier = Modifier
                .padding(bottom = 70.dp)
        )


        Image(
            painter = painterResource(R.drawable.security_badge),
            contentDescription = "Aes-256",
            modifier = Modifier.padding(bottom = 50.dp)
        )


    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}



package com.example.vault.presentation.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.R
import com.example.ui.theme.PrimaryColor
import com.example.ui.theme.TextSecondaryColor
import com.fortress.ui.theme.PassVaultTypography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    viewModel: SplashViewModel,
    onTimeout: () -> Unit
) {
    val uiState by viewModel.progressState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        delay(1500)
        onTimeout()
    }


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
            progress = { uiState.currentValue },
            modifier = Modifier
                .padding(bottom = 70.dp),
            color = PrimaryColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )


        Image(
            painter = painterResource(R.drawable.security_badge),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 50.dp)
        )


    }
}
@Preview(showBackground = true, name = "SplashScreen")
@Composable
fun PreviewSplashScreen(){

}






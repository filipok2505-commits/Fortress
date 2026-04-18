package com.example.vault.presentation.splashScreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class ProgressSplashUiState(
    val currentValue: Float = 0f,
    val isFinish: Boolean = false
)

@HiltViewModel
class SplashViewModel @Inject  constructor(): ViewModel() {
    private val _progressState = MutableStateFlow(ProgressSplashUiState())
    val progressState: StateFlow<ProgressSplashUiState> = _progressState.asStateFlow()

    init {
        startFakeLoading()
    }

    private fun startFakeLoading() {
        viewModelScope.launch {
            val duration = 500L
            val steps = 50
            val delayPerStep = duration / steps

            repeat(steps) { index ->
                _progressState.value = ProgressSplashUiState(
                    currentValue = (index + 1) / steps.toFloat(),
                    isFinish = false
                )
                delay(delayPerStep)
            }

            _progressState.value = ProgressSplashUiState(
                currentValue = 1f,
                isFinish = true
            )
        }
    }
}
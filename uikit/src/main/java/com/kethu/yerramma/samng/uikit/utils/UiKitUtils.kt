package com.kethu.yerramma.samng.uikit.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

/**
 * @Author: Yerramma Kethu
 * @Date: 16/12/2025
 */
@Composable
fun KeyboardVisibilityObserver(
    onKeyboardVisibilityChanged: (Boolean) -> Unit
) {
    val imeBottom = WindowInsets.ime.getBottom(LocalDensity.current)
    val isKeyboardVisible = remember(imeBottom) { imeBottom > 0 }

    // Send callback when visibility changes
    LaunchedEffect(isKeyboardVisible) {
        onKeyboardVisibilityChanged(isKeyboardVisible)
    }
}
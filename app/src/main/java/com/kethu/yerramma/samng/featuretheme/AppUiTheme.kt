package com.kethu.yerramma.samng.featuretheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kethu.yerramma.samng.uikit.ui.theme.AppUiThemeModel
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme

@Composable
fun AppUiTheme(
    uiTheme: AppUiThemeModel,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppUiTheme provides uiTheme) {
        content()
    }
}

enum class ThemeType(val type: String) {
    ORANGE("Orange"),
    BLUE("Blue");
}
package com.kethu.yerramma.samng.featuretheme

import com.kethu.yerramma.samng.base.BaseUiState
import com.kethu.yerramma.samng.uikit.ui.theme.AppUiThemeModel

/**
 * @Author: Yerramma Kethu
 * @Date: 08/01/2026
 */
sealed class ThemeEvent {
    data object GetCurrentTheme : ThemeEvent()
    data class SetSelectedTheme(val selectedTheme: String) : ThemeEvent()
}

data class ThemeUiState(
    val theme: AppUiThemeModel = AppUiThemeModel(),
    val selectedThemeType: String = ThemeType.ORANGE.type
) : BaseUiState

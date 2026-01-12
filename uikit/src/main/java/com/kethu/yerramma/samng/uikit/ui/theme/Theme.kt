package com.kethu.yerramma.samng.uikit.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import java.io.Serializable

@Immutable
data class AppUiThemeModel(
    val actionColor: Color = ColorSemanticWarningTwo,
    val backgroundColor: Color = ColorSemanticWarningTwo,
    val tintColor: Color = ColorSemanticWarningTwo,
    val textColor: Color = ColorTextWhite,
    val secondaryTextColor: Color = ColorSemanticWarningTwo,
    val authThemeColors: Triple<List<Color>, List<Color>, List<Color>> = Triple(
        listOf(
            Orange,
            OrangeDark
        ), listOf(OrangeLight, Orange), listOf(OrangeLight, Orange)
    )
) : Serializable

val LocalAppUiTheme = compositionLocalOf { AppUiThemeModel() }
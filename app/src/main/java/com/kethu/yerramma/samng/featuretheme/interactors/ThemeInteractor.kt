package com.kethu.yerramma.samng.featuretheme.interactors

import androidx.compose.ui.graphics.Color
import com.kethu.yerramma.samng.datastore.DataStoreConstants
import com.kethu.yerramma.samng.datastore.DataStoreRepository
import com.kethu.yerramma.samng.featuretheme.ThemeType
import com.kethu.yerramma.samng.uikit.ui.theme.AppUiThemeModel
import com.kethu.yerramma.samng.uikit.ui.theme.ColorButtonSecondaryText
import com.kethu.yerramma.samng.uikit.ui.theme.ColorInteraction
import com.kethu.yerramma.samng.utils.DEFAULT_EMPTY_STRING
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 08/01/2026
 */
class ThemeInteractor @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    suspend fun saveSelectedThemeType(theme: String) {
        dataStoreRepository.putPreference(DataStoreConstants.THEME, theme)
    }

    suspend fun getSelectedThemeUiModel(theme: String = DEFAULT_EMPTY_STRING): AppUiThemeModel {
        val finalTheme = theme.ifEmpty {
            getSelectedThemeType()
        }
        return getSelectedTheme(finalTheme)
    }

    suspend fun getSelectedThemeType() = dataStoreRepository.getPreference(
        DataStoreConstants.THEME,
        ThemeType.ORANGE.type
    )

    private fun getSelectedTheme(theme: String) = if (getThemeType(theme) == ThemeType.BLUE) {
        AppUiThemeModel(
            backgroundColor = ColorInteraction,
            tintColor = ColorInteraction,
            actionColor = ColorInteraction,
            textColor = Color.White,
            secondaryTextColor = ColorInteraction,
            authThemeColors = Triple(
                listOf(ColorButtonSecondaryText, ColorInteraction),
                listOf(ColorButtonSecondaryText, ColorInteraction),
                listOf(ColorButtonSecondaryText, ColorInteraction)
            )
        )
    } else {
        AppUiThemeModel()
    }

    private fun getThemeType(theme: String) =
        ThemeType.entries.firstOrNull { it.type.equals(theme, true) } ?: ThemeType.ORANGE
}
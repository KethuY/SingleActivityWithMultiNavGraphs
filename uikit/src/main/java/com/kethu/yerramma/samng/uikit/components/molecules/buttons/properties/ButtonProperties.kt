package com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.kethu.yerramma.samng.uikit.components.BaseView

/**
 * @Author: Yerramma Kethu
 * @Date: 10/10/2025
 */
interface ButtonProperties : BaseView {
    val text: String
    val leadingIcon: Int?
    val type: ButtonType
    val colors: ButtonColors
    val height: Dp
    val shape: Shape
    val paddingValues: PaddingValues
    val elevation: Dp
    val iconEndPadding: Dp
    val rippleEnabled: Boolean
    val alignment: Alignment
    val borderWidth: Dp
    val leadingIconSize: Dp
    val textStyle: TextStyle
    val enable: Boolean
    override val backgroundColor: Color
        get() = Color.Unspecified
}

@Stable
class ButtonColors(
    val backgroundColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val iconTintColor: Color = Color.Unspecified
)

enum class ButtonType { PRIMARY, SECONDARY }
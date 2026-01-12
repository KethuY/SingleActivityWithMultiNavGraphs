package com.kethu.yerramma.samng.uikit.components.molecules.buttons.uidatamodels

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonColors
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonProperties
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingLarge
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingMedium
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingSmall
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyMedium
import com.kethu.yerramma.samng.uikit.utils.ShapeRoundedMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 10/10/2025
 */
data class ButtonUiDataModel(
    override val text: String,
    override val leadingIcon: Int? = null,
    override val enable: Boolean = true,
    override val type: ButtonType = ButtonType.PRIMARY,
    override val colors: ButtonColors = getPrimaryButtonColors(type),
    override val paddingValues: PaddingValues = PaddingValues(horizontal = SizeSpacingMedium.dp),
    override val height: Dp = 40.dp,
    override val shape: Shape = ShapeRoundedMedium,
    override val elevation: Dp = Dp.Unspecified,
    override val iconEndPadding: Dp = SizeSpacingSmall.dp,
    override val rippleEnabled: Boolean = getRippleEnabled(type),
    override val alignment: Alignment = Alignment.Center,
    override val borderWidth: Dp = if (type == ButtonType.SECONDARY) 1.dp else Dp.Unspecified,
    override val leadingIconSize: Dp = SizeSpacingLarge.dp,
    override val textStyle: TextStyle = Style16BodyMedium.copy(
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    ),
) : ButtonProperties

private fun getPrimaryButtonColors(type: ButtonType) = if (ButtonType.SECONDARY == type)
    ButtonColors(backgroundColor = Color.Transparent)
else
    ButtonColors()


private fun getRippleEnabled(type: ButtonType) =
    type != ButtonType.SECONDARY
package com.kethu.yerramma.samng.uikit.components.molecules.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.takeOrElse
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonProperties
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import kotlinx.coroutines.delay

/**
 * AdibUiButton - Custom UI button component for Adib Mobile UIKit.
 * Author: Yerramma-Kethu_adib
 * Date: 2024-06-10
 */
@Composable
fun UiButton(
    properties: ButtonProperties,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    with(properties) {
        val tweenAnimation: TweenSpec<Color> = tween(300)

        val textThemeColor =
            if (type == ButtonType.SECONDARY) LocalAppUiTheme.current.secondaryTextColor else LocalAppUiTheme.current.textColor

        val (textColor, iconTintColor) = Pair(
            colors.textColor.takeOrElse { textThemeColor},
            colors.iconTintColor.takeOrElse { LocalAppUiTheme.current.tintColor }
        )
        var isClicked by remember { mutableStateOf(false) }
        val toggleTextColor = animateColorAsState(
            targetValue = if (isClicked) textColor.copy(alpha = 0.6f) else textColor,
            tweenAnimation
        )
        val toggleIconColor = animateColorAsState(
            targetValue = if (isClicked) iconTintColor.copy(alpha = 0.6f) else iconTintColor,
            tweenAnimation
        )
        val toggleBorderColor =
            if (isClicked) colors.borderColor.copy(alpha = 0.6f) else colors.borderColor
        LaunchedEffect(isClicked) {
            if (isClicked) {
                delay(150L)
                onClick.invoke()
                isClicked = false
            }
        }
        val boxModifier = modifier.buttonModifier(
            properties = properties,
            borderColor = toggleBorderColor.takeOrElse { LocalAppUiTheme.current.backgroundColor },
            onClick = { isClicked = true },
            backgroundColor = colors.backgroundColor.takeOrElse { LocalAppUiTheme.current.backgroundColor },
            interactionSource = remember { MutableInteractionSource() }
        )
        Box(
            modifier = boxModifier,
            contentAlignment = alignment
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                leadingIcon?.let {
                    CustomImage(
                        modifier = Modifier
                            .padding(end = iconEndPadding)
                            .size(leadingIconSize),
                        properties = ImageUiDataModel(
                            src = leadingIcon,
                            colorFilter = ColorFilter.tint(toggleIconColor.value)
                        )
                    )
                }

                CustomText(
                    properties = TextUiDataModel(
                        text = text,
                        textStyle = textStyle.copy(color = toggleTextColor.value)
                    )
                )
            }
        }
    }
}

private fun Modifier.buttonModifier(
    properties: ButtonProperties,
    backgroundColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource
) = with(properties) {
    this@buttonModifier
        .height(height)
        .shadow(elevation, shape)
        .clip(shape)
        .background(backgroundColor)
        .border(borderWidth, borderColor, shape)
        .clickable(
            interactionSource = interactionSource,
            indication = if (rippleEnabled) ripple() else null,
            enabled = enable,
            onClick = onClick
        )
        .padding(paddingValues)
}
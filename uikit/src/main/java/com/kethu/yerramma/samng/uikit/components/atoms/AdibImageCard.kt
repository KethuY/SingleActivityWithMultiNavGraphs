package com.kethu.yerramma.samng.uikit.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import com.kethu.yerramma.samng.uikit.components.atoms.properties.ImageCardProperties
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel

@Composable
fun ImageCard(
    properties: ImageCardProperties,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    with(properties) {
        Box(
            modifier = modifier
                .clip(cardShape) // to get the proper ripple effect within the shape
                .size(cardSize)
                .clickable(
                    indication = if (isActionCard) ripple() else null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        if (isActionCard) {
                            onClick()
                        }
                    }
                )
                .background(
                    color = backgroundColor,
                    shape = cardShape
                )
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CustomImage(properties = ImageUiDataModel(src = image, colorFilter = ColorFilter.tint(iconTintColor)))
        }
    }
}
package com.kethu.yerramma.samng.uikit.components.molecules.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties.ToolbarActionIconProperties
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties.ToolbarActionTextProperties
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties.ToolbarProperties
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingLarge
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel

/**
 * @Author: Yerramma Kethu
 * @Date: 04/10/2025
 */
@Composable
fun UiToolbar(
    properties: ToolbarProperties, modifier: Modifier = Modifier, onBackClick: () -> Unit = {}
) {
    BaseToolbar(
        modifier = modifier, properties = properties, navigationIconClick = onBackClick
    )
}

@Composable
fun UiToolbarWithTextAction(
    properties: ToolbarActionTextProperties,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    menuTextClick: () -> Unit = {}
) {
    BaseToolbar(
        modifier = modifier, properties = properties, navigationIconClick = onBackClick, actions = {
            TextButton(onClick = menuTextClick) {
                CustomText(
                    properties = TextUiDataModel(
                        text = properties.actionText, textStyle = properties.actionTextStyle
                    )
                )
            }
        })
}

@Composable
fun UiToolbarWithIconAction(
    properties: ToolbarActionIconProperties,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    menuIconClick: () -> Unit = {}
) {
    BaseToolbar(
        modifier = modifier, properties = properties, navigationIconClick = onBackClick, actions = {
            IconButton(menuIconClick) {
                CustomImage(
                    modifier = Modifier.size(SizeSpacingLarge.dp), properties = ImageUiDataModel(
                        src = properties.actionIcon,

                        )
                )
            }
        })
}

@Composable
fun UiToolbarCustomActions(
    properties: ToolbarProperties,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    BaseToolbar(
        modifier = modifier,
        properties = properties,
        navigationIconClick = onBackClick,
        actions = actions
    )
}
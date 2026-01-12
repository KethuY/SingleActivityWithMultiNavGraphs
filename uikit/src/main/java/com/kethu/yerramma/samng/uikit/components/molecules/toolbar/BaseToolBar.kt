package com.kethu.yerramma.samng.uikit.components.molecules.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomImage
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties.ToolbarProperties
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingLarge
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyMedium
import com.kethu.yerramma.samng.uikit.utils.isValidImage
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel

/**
 * @Author: Yerramma Kethu
 * @Date: 04/10/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BaseToolbar(
    properties: ToolbarProperties,
    modifier: Modifier = Modifier,
    navigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    with(properties) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                if (title.isNotBlank()) {
                    CustomText(
                        properties = TextUiDataModel(
                            text = title,
                            textStyle = Style16BodyMedium
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor
            ),
            navigationIcon = {
                if (navigationIcon.isValidImage()) {
                    IconButton(onClick = navigationIconClick) {
                        CustomImage(
                            modifier = Modifier.size(SizeSpacingLarge.dp),
                            properties = ImageUiDataModel(
                                src = properties.navigationIcon,

                                )
                        )

                    }
                }
            },
            actions = actions
        )
    }
}

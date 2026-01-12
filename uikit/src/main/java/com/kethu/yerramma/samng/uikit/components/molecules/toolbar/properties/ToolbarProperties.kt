package com.kethu.yerramma.samng.uikit.components.molecules.toolbar.properties

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.kethu.yerramma.samng.uikit.components.BaseView
import com.kethu.yerramma.samng.uikit.ui.theme.ColorBackground

/**
 * @Author: Yerramma Kethu
 * @Date: 06/10/2025
 */
interface ToolbarProperties : BaseView {
    @get:DrawableRes
    val navigationIcon: Int
    val title: String
    override val backgroundColor: Color get() = ColorBackground
}

interface ToolbarActionTextProperties : ToolbarProperties {
    val actionText: String
    val actionTextStyle: TextStyle
}

interface ToolbarActionIconProperties : ToolbarProperties {
    val actionIcon: Int
}


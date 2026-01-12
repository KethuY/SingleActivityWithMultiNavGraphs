package com.kethu.yerramma.samng.uikit.components.atoms.properties

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.kethu.yerramma.samng.uikit.components.BaseView

interface ImageCardProperties : BaseView {
    val image: Int
    val cardSize: Dp
    val paddingValues: PaddingValues
    val cardShape: Shape
    val isActionCard: Boolean
    val iconTintColor: Color
}

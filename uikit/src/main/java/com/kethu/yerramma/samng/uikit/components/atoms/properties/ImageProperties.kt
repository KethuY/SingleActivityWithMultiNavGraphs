package com.kethu.yerramma.samng.uikit.components.atoms.properties

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.kethu.yerramma.samng.uikit.components.BaseView

interface ImageProperties : BaseView {
    val src: Any?
    val contentScale: ContentScale
    val alpha: Float
    val colorFilter: ColorFilter?
    val contentDesc: String?
}
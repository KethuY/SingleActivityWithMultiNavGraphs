package com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.kethu.yerramma.samng.uikit.components.atoms.properties.ImageProperties


data class ImageUiDataModel(
    override val src: Any?,
    override val contentScale: ContentScale = ContentScale.Fit,
    override val alpha: Float = 1.0f,
    override val colorFilter: ColorFilter? = null,
    override val backgroundColor: Color = Color.Transparent,
    override val contentDesc: String? =null
) : ImageProperties

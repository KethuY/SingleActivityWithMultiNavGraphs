package com.kethu.yerramma.samng.uikit.components.atoms.properties

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.kethu.yerramma.samng.uikit.components.BaseView

interface TextProperties : BaseView {
    val text: String
    val textStyle: TextStyle
    val maxLines: Int get() = Int.MAX_VALUE
    val overflow: TextOverflow get() = TextOverflow.Companion.Clip
    val softWrap: Boolean get() = true
}
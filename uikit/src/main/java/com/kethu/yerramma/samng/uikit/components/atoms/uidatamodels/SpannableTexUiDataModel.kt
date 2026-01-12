package com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.kethu.yerramma.samng.uikit.components.atoms.properties.SpannableTexProperties

data class SpannableTexUiDataModel(
    override val spannableText: AnnotatedString,
    override val text: String = "",
    override val textStyle: TextStyle = TextStyle.Default,
    override val maxLines: Int = Int.MAX_VALUE,
    override val overflow: TextOverflow = TextOverflow.Clip,
    override val softWrap: Boolean = true,
    override val backgroundColor: Color = Color.Unspecified,
) : SpannableTexProperties
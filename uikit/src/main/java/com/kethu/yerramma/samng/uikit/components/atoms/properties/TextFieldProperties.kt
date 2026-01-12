package com.kethu.yerramma.samng.uikit.components.atoms.properties

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.kethu.yerramma.samng.uikit.components.BaseView
import com.kethu.yerramma.samng.uikit.ui.theme.ColorBorder
import com.kethu.yerramma.samng.uikit.utils.CONST_ONE

/**
 * @Author: Zeeshan Shahid
 * @Date: 03/06/2025
 * Interface representing the properties of a text field component in the Adib UI Kit.
 *
 * @property enabled Whether the text field is enabled or not.
 * @property singleLine Whether the text field is single line.
 * @property visualTransformation The visual transformation for the text field.
 */
interface TextFieldProperties : BaseView {
    val text: String
    val textStyle: TextStyle
    val maxLines: Int get() = Int.MAX_VALUE
    val singleLine: Boolean get() = maxLines == CONST_ONE
    val cursorColor: Color get() = ColorBorder
    val enabled: Boolean get() = true
    val readOnly: Boolean get() = false
    val keyboardOptions: KeyboardOptions
    val keyboardActions: KeyboardActions get() = KeyboardActions.Default
    val visualTransformation: VisualTransformation get() = VisualTransformation.Companion.None
}
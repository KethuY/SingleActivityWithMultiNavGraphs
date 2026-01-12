package com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.properties.TextFieldProperties
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeRadiusXSmall
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyRegular

/**
 * @Author: Yerramma Kethu
 * @Date: 02/11/2025
 */
interface InputProperties : TextFieldProperties {
    val hint: String?
    val isError: Boolean get() = false
    val isSelectable: Boolean get() = false
    val inputLength: Int get() = Int.MAX_VALUE
    val prefix: Any? get() = null
    val regex: Regex? get() = null
    val isFocusedBorderRequired: Boolean get() = true
    val isBorderInputField: Boolean get() = false
    val shape: Shape get() = RoundedCornerShape(SizeRadiusXSmall.dp)
    val height: Dp
    val horizontalArrangement: Arrangement.Horizontal
    override val backgroundColor: Color get() = Color.White
    override val textStyle: TextStyle get() = Style16BodyRegular
    override val keyboardOptions: KeyboardOptions get() = KeyboardOptions.Default
    override val singleLine: Boolean get() = maxLines == 1
}
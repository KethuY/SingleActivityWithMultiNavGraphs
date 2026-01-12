package com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import com.kethu.yerramma.samng.uikit.components.atoms.properties.TextFieldProperties
import com.kethu.yerramma.samng.uikit.ui.theme.ColorBorder
import com.kethu.yerramma.samng.uikit.utils.CONST_ONE

data class TextFieldUiDataModel(
    override val text: String,
    override val textStyle: TextStyle,
    override val cursorColor: Color = ColorBorder,
    override val enabled: Boolean = true,
    override val readOnly: Boolean = false,
    override val maxLines: Int = Int.MAX_VALUE,
    override val singleLine: Boolean = maxLines == CONST_ONE,
    override val keyboardOptions: KeyboardOptions = KeyboardOptions.Companion.Default.copy(
        autoCorrectEnabled = false,
        capitalization = KeyboardCapitalization.None
    ),
    override val keyboardActions: KeyboardActions = KeyboardActions.Companion.Default,
    override val visualTransformation: VisualTransformation = VisualTransformation.Companion.None,
    override val backgroundColor: Color = Color.Companion.Unspecified,
) : TextFieldProperties
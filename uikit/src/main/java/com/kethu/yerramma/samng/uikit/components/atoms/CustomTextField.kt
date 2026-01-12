package com.kethu.yerramma.samng.uikit.components.atoms

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import com.kethu.yerramma.samng.uikit.components.atoms.properties.TextFieldProperties

@Composable
fun CustomTextField(
    modifier: Modifier= Modifier,
    adibTextFieldUiModel: TextFieldProperties,
    onValueChange: (String) -> Unit,
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = @Composable { innerTextField -> innerTextField() }
) {
    with(adibTextFieldUiModel) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly,
            maxLines = maxLines,
            textStyle = textStyle,
            cursorBrush = SolidColor(cursorColor),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = decorationBox
        )
    }
}
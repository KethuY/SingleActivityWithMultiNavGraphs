package com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.CustomTextField
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.InputListeners
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.InputProperties
import com.kethu.yerramma.samng.uikit.ui.theme.ColorInputsPlaceholder
import com.kethu.yerramma.samng.uikit.ui.theme.ColorSemanticErrorTwo
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.utils.CONST_ONE
import com.kethu.yerramma.samng.uikit.utils.KeyboardVisibilityObserver
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextFieldUiDataModel
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel

/**
 * Author: Kethu Yerramma
 * Created on: 12-11-2025
 * I---------I------------------I-----------------I
 * I--Header-I--Info Icon-------------------------I
 * I Prefix I ------Edit Text------------ Suffix--I
 * I Description / Error Text --------------------I
 **/
@Composable
internal fun BaseInputTextField(
    textInputData: InputProperties,
    listeners: InputListeners,
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null
) {

    with(textInputData) {
        var isFocused by remember { mutableStateOf(false) }
        val focusManger = LocalFocusManager.current
        val borderColor = when {
            !isFocusedBorderRequired || isSelectable -> Color.Transparent
            isBorderInputField -> LocalAppUiTheme.current.backgroundColor
            isError -> ColorSemanticErrorTwo
            isFocused -> ColorInputsPlaceholder
            else -> Color.Transparent
        }
        val clickModifier = if (isSelectable) Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {
                listeners.onSelectionClick.invoke()
            }
        ) else Modifier
        KeyboardVisibilityObserver { isVisible ->
            if (!isVisible) {
                focusManger.clearFocus()
                isFocused = false
            }
        }
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .border(
                    width = CONST_ONE.dp,
                    color = borderColor,
                    shape = shape
                )
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .padding(horizontal = 12.dp)
                .height(height)
                .then(clickModifier)
        ) {
            prefix?.invoke()
            CustomTextField(
                modifier = Modifier
                    .onFocusChanged { focus ->
                        isFocused = focus.isFocused
                        listeners.onFocused.invoke(isFocused)
                    },
                adibTextFieldUiModel = TextFieldUiDataModel(
                    text = text,
                    textStyle = textStyle.copy(textDecoration = TextDecoration.None),
                    enabled = !isSelectable,
                    readOnly = isSelectable,
                    maxLines = maxLines,
                    singleLine = maxLines == CONST_ONE,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions {
                        focusManger.clearFocus()
                        isFocused = false
                        listeners.onImeActionClick.invoke()
                    },
                    visualTransformation = visualTransformation
                ),
                decorationBox = { innerTextField ->
                    if (text.isEmpty() && !hint.isNullOrBlank()) {
                        CustomText(
                            properties = TextUiDataModel(
                                text = hint.orEmpty(),
                                textStyle = textStyle.copy(color = LocalAppUiTheme.current.backgroundColor)
                            )
                        )
                    }
                    innerTextField()
                },
                onValueChange = { inputText ->
                    if (!isSelectable && (inputText.isEmpty() || regex == null || inputText.length <= inputLength && regex?.matches(
                            inputText
                        ) == true)
                    ) {
                        listeners.onValueChange.invoke(inputText)
                    }
                }
            )
            suffix?.invoke()
        }
    }
}
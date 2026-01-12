package com.kethu.yerramma.samng.uikit.components.atoms

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextLayoutResult
import com.kethu.yerramma.samng.uikit.components.atoms.properties.SpannableTexProperties
import com.kethu.yerramma.samng.uikit.utils.CONST_ZERO

@Composable
fun UiSpannableText(
    modifier: Modifier = Modifier,
    properties: SpannableTexProperties,
    spannableClick: ((String, String) -> Unit)? = null
) {
    with(properties) {
        val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
        val hasUrlTag = spannableText.getStringAnnotations(start = CONST_ZERO, end = spannableText.length).isNotEmpty()
        val spanModifier = if (hasUrlTag) {
            modifier.pointerInput(spannableClick) {
                detectTapGestures { pos ->
                    layoutResult.value?.let { layoutResult ->
                        val offset = layoutResult.getOffsetForPosition(pos)
                        val spannedText = spannableText.getStringAnnotations(start = offset, end = offset).firstOrNull()
                        if (spannedText != null) {
                            spannableClick?.invoke(spannedText.tag, spannedText.item)
                        }
                    }
                }
            }
        } else {
            modifier
        }
        Text(
            text = spannableText,
            modifier = spanModifier,
            style = textStyle,
            maxLines = maxLines,
            overflow = overflow,
            softWrap = softWrap,
            onTextLayout = {
                layoutResult.value = it
            }
        )
    }
}
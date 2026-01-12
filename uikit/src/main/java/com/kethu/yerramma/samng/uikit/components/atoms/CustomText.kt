package com.kethu.yerramma.samng.uikit.components.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kethu.yerramma.samng.uikit.components.atoms.properties.TextProperties

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    properties: TextProperties
) {
    properties.run {
        Text(
            modifier = modifier,
            text = text,
            style = textStyle,
            overflow = overflow,
            maxLines = maxLines,
            softWrap = softWrap
        )
    }
}
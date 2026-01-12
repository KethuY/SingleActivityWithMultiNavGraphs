package com.kethu.yerramma.samng.uikit.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.kethu.yerramma.samng.uikit.components.atoms.properties.ImageProperties


@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    properties: ImageProperties
) {
    properties.run {
        (src as? Int)?.let {
            Image(
                modifier = modifier,
                painter = painterResource(id = it),
                contentDescription = contentDesc,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        } ?: run {
            AsyncImage(
                modifier = modifier,
                model = src,
                contentDescription = contentDesc,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter
            )
        }
    }
}
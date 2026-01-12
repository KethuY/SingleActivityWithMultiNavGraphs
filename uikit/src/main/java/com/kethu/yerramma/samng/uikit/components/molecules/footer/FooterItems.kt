package com.kethu.yerramma.samng.uikit.components.molecules.footer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.components.atoms.ImageCard
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.ImageCardUiDataModel
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingSmall
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.utils.ShapeRoundedDefault

@Composable
fun FooterItems(
    modifier: Modifier = Modifier,
    icons: List<Int>,     // pass your real icons here
    onClick: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SizeSpacingSmall.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEach { iv ->
            ImageCard(
                properties = ImageCardUiDataModel(
                    image = iv,
                    backgroundColor = LocalAppUiTheme.current.backgroundColor,
                    cardSize = 32.dp,
                    cardShape = ShapeRoundedDefault,
                    paddingValues = PaddingValues(SizeSpacingSmall.dp),
                    isActionCard = true,
                    iconTintColor = Color.White
                ),
                onClick = {
                    onClick(iv)
                }
            )
        }
    }
}
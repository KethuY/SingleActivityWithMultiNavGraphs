package com.kethu.yerramma.samng.uikit.components.atoms

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.uikit.ui.theme.Orange
import com.kethu.yerramma.samng.uikit.ui.theme.OrangeLight
import kotlin.math.min


@Composable
fun BottomWave(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(OrangeLight, Orange)
) {
    val cfg = LocalConfiguration.current
    val isLandscape = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE
    val bottomHeight = if (isLandscape) 120.dp else 240.dp

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(bottomHeight)
            .navigationBarsPadding() // avoid overlap with gesture/nav bar
    ) {
        val w = size.width
        val h = size.height.coerceAtLeast(1f)

        val amp = min(w * 0.14f, h * 0.65f)
        val aS = amp * 0.45f
        val aM = amp * 0.80f
        val aL = amp * 1.10f

        val path = Path().apply {
            // Build bottom curve from lower area upward
            moveTo(0f, h - (aL * 0.9f))
            quadraticTo(
                w * 0.25f, h - (aM * 1.05f),
                w * 0.55f, h - (aS * 0.7f)
            )
            quadraticTo(
                w * 0.80f, h - (aS * 0.25f),
                w,        h - (aM)
            )
            lineTo(w, h); lineTo(0f, h)
            close()
        }

        drawPath(
            path,
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(0f, 0f),
                end   = Offset(0f, h)
            ),
            style = Fill
        )
    }
}




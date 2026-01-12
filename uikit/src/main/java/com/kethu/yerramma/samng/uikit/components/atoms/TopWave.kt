package com.kethu.yerramma.samng.uikit.components.atoms

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.kethu.yerramma.samng.uikit.ui.theme.OrangeDark
import com.kethu.yerramma.samng.uikit.ui.theme.OrangeLight
import com.kethu.yerramma.samng.uikit.ui.theme.SurfaceWhite
import kotlin.math.min


@Composable
fun TopWave(
    modifier: Modifier = Modifier,
    topColors: List<Color> = listOf(Orange, OrangeDark),
    bottomColors: List<Color> = listOf(OrangeLight, Orange),
) {
    val cfg = LocalConfiguration.current
    val isLandscape = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Give the wave real vertical space in both orientations.
    val topHeight = if (isLandscape) 80.dp else 240.dp

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(topHeight)
            .statusBarsPadding()   // avoid clipping under status bar
    ) {
        val w = size.width
        val h = size.height.coerceAtLeast(1f)

        // Amplitude drives curve depth. Clamp so it fits inside Canvas height.
        val amp = min(w * 0.12f, h * 0.60f)     // width-based, clamped to 60% of height
        val ampS = amp * 0.40f
        val ampM = amp * 0.75f
        val ampL = amp * 1.10f

        // Background gradient band
        val bgPath = Path().apply {
            moveTo(0f, ampS)
            quadraticTo(w * 0.25f, 0f, w * 0.50f, ampM)
            quadraticTo(w * 0.75f, ampL, w, ampM)
            lineTo(w, 0f); lineTo(0f, 0f)
            close()
        }
        drawPath(
            bgPath,
            brush = Brush.linearGradient(
                colors = topColors,
                start = Offset(0f, 0f),   // keep gradient vertical
                end   = Offset(0f, h)
            ),
            style = Fill
        )

        // White highlight band
        val whitePath = Path().apply {
            moveTo(0f, ampM + ampS * 0.2f)
            quadraticTo(w * 0.22f, ampS, w * 0.45f, ampM + ampS * 0.2f)
            quadraticTo(w * 0.72f, ampL - ampS * 0.3f, w, ampM + ampS * 0.2f)
            lineTo(w, ampS); lineTo(0f, ampS * 0.35f)
            close()
        }
        drawPath(
            whitePath,
            brush = Brush.linearGradient(
                colors = listOf(SurfaceWhite.copy(0.95f), SurfaceWhite.copy(0.65f)),
                start = Offset(0f, 0f),
                end   = Offset(0f, h)
            ),
            style = Fill
        )

        // Foreground orange wave
        val wavePath = Path().apply {
            moveTo(0f, ampM)
            quadraticTo(w * 0.30f, ampS, w * 0.55f, ampM + ampS * 0.35f)
            quadraticTo(w * 0.80f, ampL, w, ampM)
            lineTo(w, h); lineTo(0f, h)
            close()
        }
        drawPath(
            wavePath,
            brush = Brush.linearGradient(
                colors = bottomColors,
                start = Offset(0f, 0f),
                end   = Offset(0f, h)
            ),
            style = Fill
        )
    }
}

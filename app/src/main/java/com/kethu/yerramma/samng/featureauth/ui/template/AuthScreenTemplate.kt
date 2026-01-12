package com.kethu.yerramma.samng.featureauth.ui.template

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.featureauth.uidatamodels.SocialMediaData
import com.kethu.yerramma.samng.uikit.components.atoms.BottomWave
import com.kethu.yerramma.samng.uikit.components.atoms.TopWave
import com.kethu.yerramma.samng.uikit.components.atoms.UiSpannableText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.SpannableTexUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.footer.FooterItems
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.ui.theme.Style14CaptionRegular
import com.kethu.yerramma.samng.utils.IntentUtils
import com.kethu.yerramma.samng.utils.getSocialMediaInfo


private const val QUESTION_SPACE = "? "

/**
 * @Author: Yerramma Kethu
 * @Date: 19/12/2025
 */

@Composable
fun AuthScreenTemplate(
    modifier: Modifier = Modifier,
    footerIcons: List<SocialMediaData> = getSocialMediaInfo(),
    topHeight: Dp = 220.dp,
    bottomHeight: Dp = 120.dp,
    bottomGap: Dp = 56.dp,
    isLoginScreen: Boolean = false,
    onSignUpClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {

    val (topColors, topBtmColors, bottomColors) = LocalAppUiTheme.current.authThemeColors
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomWave(
                modifier = modifier
                    .fillMaxWidth()
                    .height(bottomHeight),
                colors = bottomColors
            )
        }) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            TopWave(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topHeight),
                topColors = topColors,
                bottomColors = topBtmColors
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()
                Spacer(Modifier.height(bottomGap))
                if (isLoginScreen) {
                    UiSpannableText(
                        modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                onSignUpClick.invoke()
                            })
                        },
                        properties = SpannableTexUiDataModel(
                            spannableText = buildAnnotatedString {
                                append(stringResource(R.string.auth_new_user))
                                append(QUESTION_SPACE)
                                withStyle(
                                    Style14CaptionRegular.toSpanStyle().copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = LocalAppUiTheme.current.backgroundColor
                                    )
                                ) {
                                    append(stringResource(R.string.auth_sign_up))
                                }
                            },
                            textStyle = Style14CaptionRegular.copy(color = LocalAppUiTheme.current.backgroundColor)
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                }
                val context = LocalContext.current
                if (footerIcons.isNotEmpty()) {
                    FooterItems(icons = footerIcons.map { it.icon }, onClick = { icon ->
                        val url = footerIcons.firstOrNull {
                            it.icon == icon
                        }?.url.orEmpty()
                        if (url.isNotEmpty()) {
                            IntentUtils.openAppUsingDeeplink(context, url)
                        }
                    })
                }
            }
        }
    }
}
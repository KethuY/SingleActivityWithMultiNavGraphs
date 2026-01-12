package com.kethu.yerramma.samng.featureauth.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kethu.yerramma.samng.featureauth.ui.template.AuthScreenTemplate
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.UiButton
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.uidatamodels.ButtonUiDataModel
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingSmall

@Composable
fun CreateAccountScreen(
    onSignUp: () -> Unit,
    onSignIn: () -> Unit
) {
    AuthScreenTemplate {
        UiButton(
            modifier = Modifier.Companion.defaultWidth(),
            properties = ButtonUiDataModel(text = stringResource(R.string.auth_create_account)),
            onClick = onSignUp
        )
        Spacer(Modifier.height(SizeSpacingSmall.dp))
        UiButton(
            modifier = Modifier.defaultWidth(),
            properties = ButtonUiDataModel(
                text = stringResource(R.string.auth_login), type = ButtonType.SECONDARY
            ), onClick = onSignIn
        )
    }
}
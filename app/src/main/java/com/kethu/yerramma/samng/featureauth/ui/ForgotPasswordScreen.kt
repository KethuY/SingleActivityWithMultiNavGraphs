package com.kethu.yerramma.samng.featureauth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.base.BaseApplication
import com.kethu.yerramma.samng.featureauth.AuthEvent
import com.kethu.yerramma.samng.featureauth.AuthViewModel
import com.kethu.yerramma.samng.featureauth.ui.template.AuthScreenTemplate
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.UiButton
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.uidatamodels.ButtonUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.InputUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.UiInputTextField
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.AdibInputListenersImpl
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeRadiusXSmall
import com.kethu.yerramma.samng.uikit.utils.ShapeRoundedXSmall

@Composable
fun ForgotPasswordScreen(
    viewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    onSignIn: () -> Unit
) {
    val uiState= viewModel.state.collectAsStateWithLifecycle()
    AuthScreenTemplate {
        var enterCode by remember { mutableStateOf("") }
        UiInputTextField(
            modifier = Modifier.Companion
                .defaultWidth()
                .fillMaxWidth(),
            properties = InputUiDataModel(
                hint = stringResource(R.string.auth_enter_code),
                text = enterCode,
                horizontalArrangement = Arrangement.Center,
                inputLength = 6,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                regex = Regex(
                    BaseApplication.getAppContext()
                        .getString(R.string.regex_numerical_value)
                )
            ),
            listeners = AdibInputListenersImpl(onValueChange = {
                enterCode = it
            })
        )

        UiButton(
            modifier = Modifier
                .defaultWidth()
                .padding(top = SizeRadiusXSmall.dp),
            properties = ButtonUiDataModel(
                text = stringResource(R.string.auth_send),
                type = ButtonType.SECONDARY,
                shape = ShapeRoundedXSmall
            ), onClick = {
                viewModel.onAction(AuthEvent.ResetPassword(enterCode))
            }
        )
        when {
            uiState.value.isLoading -> CircularProgressIndicator()
            uiState.value.isApiSuccess -> onSignIn.invoke()
        }
    }
}
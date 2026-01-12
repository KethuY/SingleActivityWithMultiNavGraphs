package com.kethu.yerramma.samng.featureauth.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kethu.yerramma.samng.R
import com.kethu.yerramma.samng.base.BaseApplication
import com.kethu.yerramma.samng.featureauth.AuthEvent
import com.kethu.yerramma.samng.featureauth.AuthViewModel
import com.kethu.yerramma.samng.featureauth.ui.template.AuthScreenTemplate
import com.kethu.yerramma.samng.uikit.components.atoms.CustomText
import com.kethu.yerramma.samng.uikit.components.atoms.uidatamodels.TextUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.UiButton
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.uidatamodels.ButtonUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.InputUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.UiInputTextField
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.AdibInputListenersImpl
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingSmall
import com.kethu.yerramma.samng.uikit.ui.theme.LocalAppUiTheme
import com.kethu.yerramma.samng.uikit.ui.theme.Style16BodyRegular
import com.kethu.yerramma.samng.uikit.utils.ShapeRoundedXSmall

@Composable
fun SignInScreen(
    viewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    onSignUp: () -> Unit,
    onForgotPwd: () -> Unit,
    onNavigateToDashboard: () -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    AuthScreenTemplate(
        isLoginScreen = true,
        onSignUpClick = onSignUp,
    ) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        UiInputTextField(
            modifier = Modifier.Companion.defaultWidth(),
            properties = InputUiDataModel(
                prefix = R.drawable.ic_user,
                hint = stringResource(R.string.auth_hint_username),
                text = userName,
                inputLength = 30,
                regex = Regex(
                    BaseApplication.getAppContext()
                        .getString(R.string.regex_alphabets_with_spaces)
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            ),
            listeners = AdibInputListenersImpl(onValueChange = {
                userName = it
            })
        )

        UiInputTextField(
            modifier = Modifier
                .defaultWidth()
                .padding(top = SizeSpacingSmall.dp),
            properties = InputUiDataModel(
                prefix = R.drawable.ic_pwd,
                hint = stringResource(R.string.auth_hint_password),
                text = password,
                inputLength = 15,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                regex = Regex(
                    BaseApplication.getAppContext().getString(R.string.regex_special_chars)
                ),
                visualTransformation = PasswordVisualTransformation()
            ),
            listeners = AdibInputListenersImpl(onValueChange = {
                password = it
            })
        )
        UiButton(
            modifier = Modifier
                .defaultWidth()
                .padding(top = SizeSpacingSmall.dp),
            properties = ButtonUiDataModel(
                text = stringResource(R.string.auth_login),
                type = ButtonType.SECONDARY,
                shape = ShapeRoundedXSmall
            ), onClick = {
                viewModel.onAction(AuthEvent.SignIn(userName, password))
            })

        Box(
            modifier = Modifier
                .defaultWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = SizeSpacingSmall.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            CustomText(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            onForgotPwd.invoke()
                        })
                    },
                properties = TextUiDataModel(
                    text = stringResource(R.string.auth_forgot_pwd),
                    textStyle = Style16BodyRegular.copy(LocalAppUiTheme.current.backgroundColor)
                )
            )
        }

        if (uiState.value.isLoading) {
            CircularProgressIndicator(color = Color.Blue)
        }
        LaunchedEffect(uiState.value.isApiSuccess) {
            if (uiState.value.isApiSuccess) {
                onNavigateToDashboard.invoke()
            }
        }
    }
}
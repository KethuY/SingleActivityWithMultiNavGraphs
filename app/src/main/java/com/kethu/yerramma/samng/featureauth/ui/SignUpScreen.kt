package com.kethu.yerramma.samng.featureauth.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.UiButton
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.properties.ButtonType
import com.kethu.yerramma.samng.uikit.components.molecules.buttons.uidatamodels.ButtonUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.InputUiDataModel
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.UiInputTextField
import com.kethu.yerramma.samng.uikit.components.molecules.inputs.textfield.properties.AdibInputListenersImpl
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeFontWebCaption
import com.kethu.yerramma.samng.uikit.ui.theme.Dimens.SizeSpacingSmall
import com.kethu.yerramma.samng.uikit.utils.ShapeRoundedXSmall

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    navigateToDashboard:() -> Unit
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()
    AuthScreenTemplate(
        bottomGap = SizeFontWebCaption.dp,
    ) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        val context = LocalContext.current

        UiInputTextField(
            modifier = Modifier.defaultWidth(),
            properties = InputUiDataModel(
                prefix = R.drawable.ic_user,
                hint = stringResource(R.string.auth_hint_username),
                text = userName,
                inputLength = 30,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                regex = Regex(
                    BaseApplication.getAppContext()
                        .getString(R.string.regex_alphabets_with_spaces)
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
                prefix = R.drawable.ic_round_email_24,
                hint = stringResource(R.string.auth_hint_email),
                text = email,
                inputLength = 100,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                regex = Regex(
                    BaseApplication.getAppContext().getString(R.string.regex_special_chars)
                )

            ),
            listeners = AdibInputListenersImpl(onValueChange = {
                email = it
            })
        )

        UiInputTextField(
            modifier = Modifier
                .defaultWidth()
                .padding(top = SizeSpacingSmall.dp),
            properties = InputUiDataModel(
                prefix = R.drawable.ic_call,
                hint = stringResource(R.string.auth_hint_phone),
                text = phone,
                inputLength = 10,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                regex = Regex(
                    BaseApplication.getAppContext().getString(R.string.regex_numerical_value)
                )

            ),
            listeners = AdibInputListenersImpl(onValueChange = {
                phone = it
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
                visualTransformation = PasswordVisualTransformation(),
                inputLength = 15,
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                regex = Regex(
                    BaseApplication.getAppContext().getString(R.string.regex_special_chars)
                )
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
                text = stringResource(R.string.auth_register),
                type = ButtonType.SECONDARY,
                shape = ShapeRoundedXSmall
            ), onClick = {
                viewModel.onAction(
                    AuthEvent.SignUp(
                        userName = userName,
                        password = password,
                        email = email,
                        phone = phone
                    )
                )
            })

        if (uiState.value.isLoading) {
            CircularProgressIndicator(color = Color.Blue)
        }
        LaunchedEffect(uiState.value.isApiSuccess) {
            if (uiState.value.isApiSuccess) {
                navigateToDashboard.invoke()
            }
        }
    }
}


internal fun Modifier.defaultWidth(): Modifier = this
    .width(250.dp)
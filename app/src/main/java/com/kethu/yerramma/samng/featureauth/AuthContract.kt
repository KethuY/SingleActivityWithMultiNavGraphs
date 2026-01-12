package com.kethu.yerramma.samng.featureauth

import com.kethu.yerramma.samng.base.BaseUiEffect
import com.kethu.yerramma.samng.base.BaseUiState

/**
 * @Author: Yerramma Kethu
 * @Date: 27/12/2025
 */
sealed class AuthEvent {
    data class SignIn(val userName: String, val password: String) :
        AuthEvent()

    data class SignUp(
        val userName: String,
        val password: String,
        val phone: String,
        val email: String
    ) : AuthEvent()

    data class ResetPassword(
        val enteredCode: String
    ) : AuthEvent()
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val isApiSuccess: Boolean = false,
) : BaseUiState

sealed interface AuthUiEffect : BaseUiEffect {
    data object NavigateToDashboard : AuthUiEffect
}
package com.kethu.yerramma.samng.featureauth.interactor

import com.kethu.yerramma.samng.featureauth.repository.request.ForgotPwdRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignInRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignUpRequest
import com.kethu.yerramma.samng.featureauth.repository.AuthRepository
import com.kethu.yerramma.samng.networkmodule.client.ApiBaseResponse
import com.kethu.yerramma.samng.networkmodule.client.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 27/12/2025
 */
class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) {

    fun doSignUp(
        userName: String,
        password: String,
        email: String,
        phone: String
    ): Flow<Resource<ApiBaseResponse>> = authRepository.signUp(
        SignUpRequest(
            username = userName,
            password = password,
            email = email,
            phone = phone
        )
    )

    fun doSignIn(
        userName: String,
        password: String
    ): Flow<Resource<ApiBaseResponse>> = authRepository.signIn(
        SignInRequest(
            username = userName,
            password = password
        )
    )

    fun resetPassword(enteredCode: String): Flow<Resource<ApiBaseResponse>> =
        authRepository.resetPassword(ForgotPwdRequest(enteredCode = enteredCode))
}
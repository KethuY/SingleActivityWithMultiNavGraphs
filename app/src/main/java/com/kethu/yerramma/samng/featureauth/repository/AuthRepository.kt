package com.kethu.yerramma.samng.featureauth.repository

import com.kethu.yerramma.samng.featureauth.repository.request.ForgotPwdRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignInRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignUpRequest
import com.kethu.yerramma.samng.networkmodule.client.ApiBaseResponse
import com.kethu.yerramma.samng.networkmodule.client.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(
      signUpRequest: SignUpRequest
    ): Flow<Resource<ApiBaseResponse>> // return type need to update

    fun signIn(
       signInRequest: SignInRequest
    ): Flow<Resource<ApiBaseResponse>> // return type need to update

    fun resetPassword(forgotPwdRequest: ForgotPwdRequest): Flow<Resource<ApiBaseResponse>> // return type need to update
}
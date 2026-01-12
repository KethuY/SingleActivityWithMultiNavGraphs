package com.kethu.yerramma.samng.featureauth.repository

import com.google.gson.reflect.TypeToken
import com.kethu.yerramma.samng.featureauth.repository.request.ForgotPwdRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignInRequest
import com.kethu.yerramma.samng.featureauth.repository.request.SignUpRequest
import com.kethu.yerramma.samng.utils.getJsonString
import com.kethu.yerramma.samng.networkmodule.client.ApiBaseResponse
import com.kethu.yerramma.samng.networkmodule.client.ApiRequestBuilder
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSource
import com.kethu.yerramma.samng.networkmodule.client.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: Yerramma Kethu
 * @Date: 27/12/2025
 */
class AuthRepositoryImpl @Inject constructor(private val dataSource: NetworkDataSource) :
    AuthRepository {
    override fun signUp(
        signUpRequest: SignUpRequest
    ): Flow<Resource<ApiBaseResponse>> = dataSource.send(
        ApiRequestBuilder.createBasicPostRequest(
            "signUp",// need to keep actual
            object : TypeToken<ApiBaseResponse>() {}.type
        ).apply {
            setBody(getJsonString(signUpRequest))
        }
    )

    override fun signIn(
        signInRequest: SignInRequest
    ): Flow<Resource<ApiBaseResponse>> = dataSource.send(
        ApiRequestBuilder.createBasicPostRequest(
            "signIn",// need to keep actual
            object : TypeToken<ApiBaseResponse>() {}.type
        ).apply {
            setBody(getJsonString(signInRequest))
        }
    )

    override fun resetPassword(forgotPwdRequest: ForgotPwdRequest): Flow<Resource<ApiBaseResponse>> =
        dataSource.send(
            ApiRequestBuilder.createBasicPostRequest(
                "resetPassword",// need to keep actual
                object : TypeToken<ApiBaseResponse>() {}.type
            ).apply {
                setBody(getJsonString(forgotPwdRequest))
            }
        )
}
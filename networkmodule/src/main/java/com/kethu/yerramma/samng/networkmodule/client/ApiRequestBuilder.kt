package com.kethu.yerramma.samng.networkmodule.client

import java.lang.reflect.Type

object ApiRequestBuilder {
    fun createBasicGetRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.GET)
        return apiRequest
    }

    fun createBasicPostFormUrlEncodedRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.POST_FORM_URL_ENCODED)
        return apiRequest
    }

    fun createBasicPostRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.POST)
        return apiRequest
    }

    fun createBasicMultipartPostRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.POST)
        return apiRequest
    }

    fun createBasicPostRequestForOtp(
        path: String,
        token: Type,
        isMultipartRequest: Boolean
    ): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.POST)
        return apiRequest
    }

    fun createBasicPutRequestForOtp(
        path: String,
        token: Type,
        isMultipartRequest: Boolean
    ): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.PUT)
        return apiRequest
    }

    fun createBasicPostRequestForOtpWithActionType(
        path: String,

        token: Type,
        isMultipartRequest: Boolean,
        actionType: String,
        placeHolders: String
    ): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.POST)
        return apiRequest
    }

    fun createBasicPutRequestForOtpWithActionType(
        path: String,

        token: Type,
        isMultipartRequest: Boolean,
        actionType: String,
        placeHolders: String
    ): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.PUT)
        return apiRequest
    }

    fun createBasicPutRequestForAuthWithActionType(
        path: String,

        token: Type,
        isMultipartRequest: Boolean,
        actionType: String,
        placeHolders: String
    ): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.PUT)
        return apiRequest
    }

    fun createBasicPatchRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.PATCH)
        return apiRequest
    }

    fun createBasicDeleteRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.DELETE)
        return apiRequest
    }

    private fun getBaseRequest(path: String, token: Type): ApiRequest {
        val api = ApiRequest()
        api.setPath(path)
        api.setTypeToken(token)
        return api
    }

    fun createBasicPutRequest(path: String, token: Type): ApiRequest {
        val apiRequest = getBaseRequest(path, token)
        apiRequest.setRequestMethod(RequestMethod.PUT)
        return apiRequest
    }

}
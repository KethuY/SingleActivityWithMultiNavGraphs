package com.kethu.yerramma.samng.networkmodule.client


/**
 * Created by: Jawad Usman
 * Email: jawad.usman@nagarro.com
 * Company: Nagarro
 * Date: 12,September,2022
 * Copyright: ADIB 2022-2023
 */
sealed class Resource<T>(
    val data: T? = null,
    val onErrorResponse: ErrorResponse? = null,
    val networkState: NetworkState? = null,
) : TransformResponse {

    class Success<T>(data: T?, networkState: NetworkState?) :
        Resource<T>(data, networkState = networkState) {

        override fun <V> getResponse(): V? {
            return data as? V
        }

        override fun apiNetworkState(): NetworkState? {
            return networkState
        }
    }

    class Error<T>(
        errorResponse: ErrorResponse?,
        data: T? = null,
        networkState: NetworkState? = null
    ) : Resource<T>(data, errorResponse, networkState) {

        override fun getError(): ErrorResponse? {
            return onErrorResponse
        }

        override fun apiNetworkState(): NetworkState? {
            return networkState
        }
    }

    class Network<T>(networkState: NetworkState?, data: T? = null) :
        Resource<T>(data, networkState = networkState) {
        override fun apiNetworkState(): NetworkState? {
            return networkState
        }
    }

    fun isSuccess() = networkState == NetworkState.SUCCESS
    fun isFailure() = networkState == NetworkState.FAILED
}

interface TransformResponse {
    fun getError(): ErrorResponse? {
        return null
    }

    fun <V> getResponse(): V? {
        return null
    }

    fun apiNetworkState(): NetworkState? {
        return null
    }
}

data class ErrorResponse(
    val code: String? = null,
    val title: String? = null,
    val desc: String? = null
)
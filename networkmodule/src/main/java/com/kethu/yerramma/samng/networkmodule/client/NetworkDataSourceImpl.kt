package com.kethu.yerramma.samng.networkmodule.client

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.kethu.yerramma.samng.networkmodule.client.ErrorConstants.EXCEPTION_CODE
import com.kethu.yerramma.samng.networkmodule.client.ErrorConstants.NO_INTERNET
import com.kethu.yerramma.samng.networkmodule.client.NetworkManager.isNetworkAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber
import java.lang.reflect.Type

class NetworkDataSourceImpl(
    private val context: Context,
    private val dispatcher: DispatcherProvider,
    private val apiInterface: ApiService
) : NetworkDataSource {

    override fun <T> send(request: BaseRequest): Flow<Resource<T>> = channelFlow<Resource<T>> {
        if (isNetworkAvailable(context)) {
            Timber.d("Okhttp: Request: ${request.endpoint()} \n Params: ${request.queryParams}")
            send(Resource.Network(NetworkState.LOADING))
            val response = getApiResponse(request, apiInterface)
            if (response?.isSuccessful == true) {
                val data = getConvertedTypeTokenModel<T>(
                    response.body(),
                    request.responseType
                )
                Timber.d("Okhttp Response: ${response.body()}")
                send(Resource.Network(networkState = NetworkState.SUCCESS, data))
            } else {
                Timber.d("Okhttp Failed: ${response?.code()} ${response?.message()}")
                send(
                    Resource.Error(
                        networkState = NetworkState.FAILED,
                        errorResponse = ErrorResponse(
                            code = response?.code().toString(),
                            desc = response?.message().toString()
                        )
                    )
                )
            }

        } else {
            Timber.d("Okhttp NO Internet")
            send(
                Resource.Error(
                    networkState = NetworkState.FAILED,
                    errorResponse = ErrorResponse(code = NO_INTERNET)
                )
            )
        }
    }.catch {
        Timber.d("Okhttp Failed: Exception ${it.localizedMessage}")
        emit(Resource.Error(errorResponse = ErrorResponse(code = EXCEPTION_CODE)))
    }.flowOn(dispatcher.io())

    override fun <T> sendMultiPartRequest(request: BaseRequest): Flow<Resource<T>> =
        channelFlow<Resource<T>> {
        }.catch {
            emit(Resource.Error(errorResponse = ErrorResponse(code = EXCEPTION_CODE)))
        }.flowOn(dispatcher.io())

    private fun <T> getConvertedTypeTokenModel(
        response: JsonElement?,
        responseType: Type?,
    ): T? {
        try {
            responseType?.let { typeToken ->
                return Gson().fromJson(response, typeToken)
            }
        } catch (_: JsonParseException) {
        }
        return null
    }


    private suspend fun getApiResponse(
        request: BaseRequest,
        apiInterface: ApiService
    ): Response<JsonElement>? = when (request.requestMethod()) {
        RequestMethod.GET -> apiInterface.suspendGetRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams
        )

        RequestMethod.POST -> apiInterface.suspendedPostRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams,
            request.postBody().toString()
        )

        RequestMethod.PATCH -> apiInterface.suspendedPatchRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams,
            request.postBody().toString()
        )

        RequestMethod.POST_FORM_URL_ENCODED -> apiInterface.suspendedPostFormUrlEncodedRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams,
            request.fieldsMap
        )

        RequestMethod.PUT -> apiInterface.suspendPutRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams,
            request.postBody().toString()
        )

        RequestMethod.DELETE -> apiInterface.suspendDeleteRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams
        )

        else -> apiInterface.suspendGetRequest(
            request.requestHeaders,
            request.endpoint(),
            request.queryParams
        )
    }
}
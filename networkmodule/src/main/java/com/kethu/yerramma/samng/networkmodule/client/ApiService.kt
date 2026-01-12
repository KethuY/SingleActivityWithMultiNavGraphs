package com.kethu.yerramma.samng.networkmodule.client

import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.QueryMap

// ApiService.kt
interface ApiService {

    @GET("{end_point}")
    suspend fun suspendGetRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @QueryMap queryMap: MutableMap<String, String?>?
    ): Response<JsonElement>

    @POST("{end_point}")
    suspend fun suspendedPostRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @Body json: String?
    ): Response<JsonElement>

    @POST("{end_point}")
    suspend fun suspendedPostRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @QueryMap(encoded = true) queryMap: MutableMap<String, String?>?,
        @Body json: String?
    ): Response<JsonElement>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("{end_point}")
    suspend fun suspendedPostFormUrlEncodedRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(
            value = "end_point",
            encoded = true
        ) endPoint: String?,
        @QueryMap(encoded = true) queryMap: MutableMap<String, String?>?,
        @FieldMap fields: MutableMap<String, String?>?
    ): Response<JsonElement>

    @PUT("{end_point}")
    suspend fun suspendPutRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @QueryMap(encoded = true) queryMap: MutableMap<String, String?>?,
        @Body json: String?
    ): Response<JsonElement>

    @DELETE("{end_point}")
    suspend fun suspendDeleteRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @QueryMap(encoded = true) queryMap: MutableMap<String, String?>?
    ): Response<JsonElement>

    @Multipart
    @POST("{end_point}")
    suspend fun uploadMultipartDataWithResponseContent(
        @HeaderMap map: MutableMap<String, String?>?,
        @Part("data") data: RequestBody?,
        @Part file: List<MultipartBody.Part?>?,
        @Path(
            value = "end_point",
            encoded = true
        ) endPoint: String?
    ): Response<String?>?

    @Multipart
    @POST("{end_point}")
    suspend fun uploadMultipartDataWithNoResponseContent(
        @HeaderMap map: MutableMap<String, String?>?,
        @Part("data") data: RequestBody?,
        @Part file: List<MultipartBody.Part?>?,
        @Path(
            value = "end_point",
            encoded = true
        ) endPoint: String?
    ): Response<Unit?>?

    @PATCH("{end_point}")
    suspend fun suspendedPatchRequest(
        @HeaderMap map: MutableMap<String, String?>?,
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @QueryMap(encoded = true) queryMap: MutableMap<String, String?>?,
        @Body json: String?
    ): Response<JsonElement>
}

package com.kethu.yerramma.samng.networkmodule.client

import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.lang.reflect.Type

/**
 *  Request class to be used for setting request parameters
 */
class ApiRequest : BaseRequest() {
    private var path: String? = null
    private var body: String? = null
    private var requestMethod: String? = null
    override var responseType: Type? = null
        private set
    override var isSecureApi = true
        private set
    override var queryParams: MutableMap<String, String?> = mutableMapOf()
    override var fieldsMap: MutableMap<String, String?> = mutableMapOf()
    override var requestHeaders: MutableMap<String, String?> = mutableMapOf()

    fun setPath(path: String?) {
        this.path = path
    }

    fun setBody(body: String?) {
        this.body = body
    }

    fun setRequestMethod(requestMethod: String?) {
        this.requestMethod = requestMethod
    }

    fun setTypeToken(typeToken: Type?) {
        responseType = typeToken
    }

    override fun endpoint(): String {
        return path ?: ""
    }

    override fun requestMethod(): String? {
        return requestMethod
    }

    override fun postBody(): JSONObject {
        try {
            if (!body.isNullOrBlank()) {
                Timber.d("Okhttp: Body: $body")
                return JSONObject(body ?: "")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return JSONObject()
    }
}
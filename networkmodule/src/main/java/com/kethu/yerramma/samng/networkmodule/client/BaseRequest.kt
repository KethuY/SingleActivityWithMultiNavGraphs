package com.kethu.yerramma.samng.networkmodule.client

import org.json.JSONObject
import java.lang.reflect.Type

abstract class BaseRequest {
    var fileDataMap: Map<String, ByteArray>? = null
    var filePaths: List<String>? = null
    var fileKeys: List<String?>? = null
    var hasMultipartResponseContent = false
    abstract val isSecureApi: Boolean
    abstract fun endpoint(): String
    abstract fun requestMethod(): String?
    abstract fun postBody(): JSONObject
    abstract val responseType: Type?

    open val queryParams: MutableMap<String, String?>
        get() = mutableMapOf()
    open val fieldsMap: MutableMap<String, String?>?
        get() = mutableMapOf()

    open val requestHeaders: MutableMap<String, String?>
        get() = mutableMapOf()
}
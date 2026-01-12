package com.kethu.yerramma.samng.networkmodule.client

import androidx.annotation.StringDef

object RequestMethod {
    const val POST_FORM_URL_ENCODED = "POST_FORM_URL_ENCODED"
    const val GET = "GET"
    const val POST = "POST"
    const val PATCH = "PATCH"
    const val PUT = "PUT"
    const val DELETE = "DELETE"
    @StringDef(
        POST_FORM_URL_ENCODED,
        GET,
        POST,
        PATCH,
        PUT,
        DELETE
    )
    @Retention(AnnotationRetention.SOURCE) // <-- Kotlin retention
    @Target(
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FIELD,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.TYPE,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.LOCAL_VARIABLE
    )
    annotation class RequestMethods

}



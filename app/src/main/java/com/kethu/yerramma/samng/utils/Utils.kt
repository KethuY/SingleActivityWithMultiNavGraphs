package com.kethu.yerramma.samng.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.navigation.NavDestination
import com.google.gson.Gson
import com.kethu.yerramma.samng.base.BaseApplication

/**
 * @Author: Yerramma Kethu
 * @Date: 05/01/2026
 */
fun getJsonString(jsonElement: Any): String? = Gson().toJson(jsonElement)

fun getStringFromRes(resId: Int): String =
    BaseApplication.getAppContext().resources.getString(resId)

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

val NavDestination.simpleRoute: String?
    get() = route?.substringAfterLast(DEFAULT_DOT_STRING)?.substringBefore(DEFAULT_QUESTION_STRING)
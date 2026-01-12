package com.kethu.yerramma.samng.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

object IntentUtils {

    fun openUrlInBrowser(context: Context, browserUrl: Uri?, excludeAdibApp: Boolean) {
        val browserIntent = Intent(Intent.ACTION_VIEW, browserUrl)
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (browserIntent.resolveActivity(context.packageManager) != null && !excludeAdibApp) {
            context.startActivity(browserIntent)
            return
        } else if (browserIntent.resolveActivity(context.packageManager) == null) {
            return
        }
        val packageManager = context.packageManager
        val activities = packageManager.queryIntentActivities(browserIntent, 0)
        val targetIntents = mutableListOf<Intent?>()
        for (currentInfo in activities) {
            val packageName = currentInfo.activityInfo.packageName
            if (context.applicationInfo.packageName != packageName) {
                val targetIntent = Intent(Intent.ACTION_VIEW, browserUrl)
                targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                targetIntent.setPackage(packageName)
                targetIntents.add(targetIntent)
            }
        }
        if (!targetIntents.isEmpty()) {
            val chooserIntent = Intent.createChooser(
                targetIntents.removeAt(0),
                "Open with"
            )
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(targetIntents))
            context.startActivity(chooserIntent)
        }
    }

    fun openAppUsingDeeplink(context: Context, reDirectUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = reDirectUrl.toUri()
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }
    }
}
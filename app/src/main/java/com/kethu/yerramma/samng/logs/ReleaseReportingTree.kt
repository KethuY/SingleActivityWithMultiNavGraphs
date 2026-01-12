package com.kethu.yerramma.samng.logs

import android.util.Log
import timber.log.Timber

class ReleaseReportingTree(
    private val piiRedactor: (String) -> String = { it } // inject a redactor if needed
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // Drop low-priority logs in release
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) return

        // Redact sensitive info
        val safeMsg = piiRedactor(message)

        // Optionally forward to a crash reporter
        // Example with Firebase Crashlytics:
        // val crashlytics = FirebaseCrashlytics.getInstance()
        // crashlytics.log("${tag.orEmpty()}: $safeMsg")
        // if (t != null && priority >= Log.ERROR) {
        //     crashlytics.recordException(t)
        // }

        // Still print to Logcat for WARN/ERROR (useful during QA on release builds)
        Log.println(priority, tag, safeMsg + (t?.let { "\n${Log.getStackTraceString(it)}" } ?: ""))
    }
}

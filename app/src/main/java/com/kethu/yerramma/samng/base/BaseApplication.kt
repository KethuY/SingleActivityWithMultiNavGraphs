package com.kethu.yerramma.samng.base

import android.app.Application
import com.kethu.yerramma.samng.logs.ReleaseReportingTree
import com.kethu.yerramma.samng.networkmodule.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var sInstance: BaseApplication
        fun getAppContext(): BaseApplication = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        if (BuildConfig.DEBUG) {
            // Debug logging to Logcat with automatic tags, line numbers
            Timber.plant(Timber.DebugTree())
        } else {
            // Production logging policy: filter, forward WARN/ERROR to crash reporting
            Timber.plant(ReleaseReportingTree())
        }
    }
}
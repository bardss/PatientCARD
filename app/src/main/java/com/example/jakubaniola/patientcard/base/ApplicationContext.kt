package com.example.jakubaniola.patientcard.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import android.view.WindowManager

import com.crashlytics.android.Crashlytics

import io.fabric.sdk.android.Fabric

class ApplicationContext : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Fabric.with(this, Crashlytics())
        registerCallbacks()
    }

    private fun registerCallbacks() {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(a: Activity, savedInstanceState: Bundle) {
                a.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            }

            override fun onActivityStarted(activity: Activity) {
                //do nothing
            }

            override fun onActivityResumed(activity: Activity) {
                //do nothing
            }

            override fun onActivityPaused(activity: Activity) {
                //do nothing
            }

            override fun onActivityStopped(activity: Activity) {
                //do nothing
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                //do nothing
            }

            override fun onActivityDestroyed(activity: Activity) {
                //do nothing
            }
        })
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    companion object {
        private var mAppContext: Context? = null

        var appContext: Context?
            get() = mAppContext
            set(mAppContext) {
                ApplicationContext.mAppContext = mAppContext
            }
    }
}

package com.patientcard.base

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

class ApplicationContext : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        MultiDex.install(this);
//        Fabric.with(this, Crashlytics())
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

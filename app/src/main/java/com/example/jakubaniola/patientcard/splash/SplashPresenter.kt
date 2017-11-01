package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import android.os.Handler
import com.example.jakubaniola.patientcard.base.BasePresenter

interface SplashPresenter : BasePresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    fun handleSplashScreen(handler: Handler)

}


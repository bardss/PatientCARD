package com.patientcard.views.splash

import android.content.Intent
import android.os.Handler
import com.patientcard.views.base.BasePresenter

interface SplashPresenter : BasePresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    fun handleSplashScreen(handler: Handler)

}


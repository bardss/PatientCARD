package com.patientcard.views.splash

import android.content.Intent
import android.os.Handler
import com.patientcard.views.base.BaseAbstractPresenter

class SplashPresenterImpl : BaseAbstractPresenter<SplashView>(), SplashPresenter {

    private val presentationModel: SplashModel by lazy { SplashModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun handleSplashScreen(handler: Handler) {
        handler.postDelayed({
            view!!.openAccessActivity()
        }, presentationModel.animDuration.toLong())
    }

}

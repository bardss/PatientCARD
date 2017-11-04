package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import android.os.Handler
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class SplashPresenterImpl : BaseAbstractPresenter<SplashView>(), SplashPresenter {

    private var model : SplashModel? = null;

    override fun initExtras(intent: Intent) {
        // no extras
    }

    val presentationModel: SplashModel
        get() {
            if (model == null) {
                model = SplashModel()
            }
            return model as SplashModel
        }

    override fun handleSplashScreen(handler: Handler) {
        handler.postDelayed({
            view!!.openAccessActivity()
        }, presentationModel.animDuration.toLong())
    }

}

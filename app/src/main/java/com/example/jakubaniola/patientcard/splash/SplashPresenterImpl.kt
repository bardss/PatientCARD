package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import android.os.Handler
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class SplashPresenterImpl : BaseAbstractPresenter<SplashView>(), SplashPresenter {

    private var model : SplashModelImpl? = null;

    override fun initExtras(intent: Intent) {
        // no extras
    }

    val presentationModel: SplashModelImpl
        get() {
            if (model == null) {
                model = SplashModelImpl()
            }
            return model as SplashModelImpl
        }

    override fun handleSplashScreen(handler: Handler) {
        handler.postDelayed({
            view!!.openAccessActivity()
        }, presentationModel.animDuration.toLong())
    }

}

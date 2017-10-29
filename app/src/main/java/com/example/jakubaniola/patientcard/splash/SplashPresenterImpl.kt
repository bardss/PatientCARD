package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class SplashPresenterImpl : BaseAbstractPresenter<SplashView>(), SplashPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

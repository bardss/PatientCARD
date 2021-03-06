package com.patientcard.views.splash

import android.content.Intent
import android.os.Handler
import com.patientcard.R
import com.patientcard.views.access.AccessActivity
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_splash, presenter = SplashPresenterImpl::class)
class SplashActivity : BaseActivity(), SplashView {

    @Presenter
    lateinit var presenter: SplashPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.handleSplashScreen(Handler())
    }

    override fun openAccessActivity() {
        startActivity(Intent(this, AccessActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}

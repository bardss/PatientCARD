package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import android.os.Handler
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.access.AccessActivity
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.rsqtechnologies.rsqphysio.base.BaseActivity
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
        startActivity(Intent(this, AccessActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}

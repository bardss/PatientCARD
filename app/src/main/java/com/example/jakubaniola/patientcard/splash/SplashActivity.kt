package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.qrreader.QRReaderActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({ openActivity() }, 1000)
    }

    private fun openActivity() {
        val activityToOpen = Intent(this, QRReaderActivity::class.java)
        startActivity(activityToOpen)
    }
}

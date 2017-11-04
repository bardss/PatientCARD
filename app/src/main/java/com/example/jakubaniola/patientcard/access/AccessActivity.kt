package com.example.jakubaniola.patientcard.access

import android.content.Intent
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.qrreader.QRReaderActivity
import com.rsqtechnologies.rsqphysio.base.BaseActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_access.*

@ActivityView(layout = R.layout.activity_access, presenter = AccessPresenterImpl::class)
class AccessActivity : BaseActivity(), AccessView {

    @Presenter
    lateinit var presenter: AccessPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        login_button.setOnClickListener { openQRReaderActivity() }
    }

    private fun openQRReaderActivity() {
        startActivity(Intent(this, QRReaderActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}

package com.patientcard.access

import android.content.Intent
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.qrreader.QRReaderActivity
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
        loginButton.setOnClickListener { openQRReaderActivity() }
    }

    private fun openQRReaderActivity() {
        startActivity(Intent(this, QRReaderActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}

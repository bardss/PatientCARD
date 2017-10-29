package com.example.jakubaniola.patientcard.qrreader

import android.os.Bundle
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.splash.QRReaderPresenter
import com.example.jakubaniola.patientcard.splash.QRReaderView
import com.rsqtechnologies.rsqphysio.base.BaseActivity
import easymvp.annotation.Presenter

class QRReaderActivity : BaseActivity(), QRReaderView {

    @Presenter
    private var presenter: QRReaderPresenter? = null

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr__reader)
    }
}

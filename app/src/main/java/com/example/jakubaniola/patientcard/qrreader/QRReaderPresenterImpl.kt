package com.example.jakubaniola.patientcard.splash

import android.content.Intent
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class QRReaderPresenterImpl : BaseAbstractPresenter<QRReaderView>(), QRReaderPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

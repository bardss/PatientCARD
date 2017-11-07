package com.patientcard.splash

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class QRReaderPresenterImpl : BaseAbstractPresenter<QRReaderView>(), QRReaderPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

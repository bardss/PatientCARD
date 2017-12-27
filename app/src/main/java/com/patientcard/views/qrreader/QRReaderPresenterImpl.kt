package com.patientcard.views.splash

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class QRReaderPresenterImpl : BaseAbstractPresenter<QRReaderView>(), QRReaderPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.shortfever

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class ShortFeverPresenterImpl : BaseAbstractPresenter<ShortFeverView>(), ShortFeverPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

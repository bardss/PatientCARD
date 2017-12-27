package com.patientcard.views.shortfever

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class ShortFeverPresenterImpl : BaseAbstractPresenter<ShortFeverView>(), ShortFeverPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

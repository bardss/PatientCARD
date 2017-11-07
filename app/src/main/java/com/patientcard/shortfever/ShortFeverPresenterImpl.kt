package com.patientcard.demographic

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter
import com.patientcard.shortfever.ShortFeverPresenter
import com.patientcard.shortfever.ShortFeverView

class ShortFeverPresenterImpl : BaseAbstractPresenter<ShortFeverView>(), ShortFeverPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.access

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.views.access

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

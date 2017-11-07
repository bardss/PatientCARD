package com.patientcard.access

import android.content.Intent
import com.patientcard.base.BasePresenter

interface AccessPresenter : BasePresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}


package com.patientcard.recommendations

import android.content.Intent
import com.patientcard.base.BasePresenter

interface RecommendationsPresenter : BasePresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}


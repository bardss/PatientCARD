package com.patientcard.recommendations

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class RecommendationsPresenterImpl : BaseAbstractPresenter<RecomendationsView>(), RecommendationsPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

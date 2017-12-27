package com.patientcard.views.recommendations

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class RecommendationsPresenterImpl : BaseAbstractPresenter<RecomendationsView>(), RecommendationsPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

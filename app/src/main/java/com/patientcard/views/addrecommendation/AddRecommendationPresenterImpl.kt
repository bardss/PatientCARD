package com.patientcard.views.addrecommendation

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class AddRecommendationPresenterImpl : BaseAbstractPresenter<AddRecommendationView>(), AddRecommendationPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.addrecommendation

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class AddRecommendationPresenterImpl : BaseAbstractPresenter<AddRecommendationView>(), AddRecommendationPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

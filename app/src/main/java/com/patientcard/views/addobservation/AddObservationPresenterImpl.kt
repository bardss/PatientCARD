package com.patientcard.views.addobservation

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter
import com.patientcard.views.recommendations.AddObservationView

class AddObservationPresenterImpl : BaseAbstractPresenter<AddObservationView>(), AddObservationPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

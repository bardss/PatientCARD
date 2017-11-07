package com.patientcard.addobservation

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter
import com.patientcard.recommendations.AddObservationView

class AddObservationPresenterImpl : BaseAbstractPresenter<AddObservationView>(), AddObservationPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.example.jakubaniola.patientcard.demographic

import android.content.Intent
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class DemographicPresenterImpl : BaseAbstractPresenter<DemographicView>(), DemographicPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.demographic

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class DemographicPresenterImpl : BaseAbstractPresenter<DemographicView>(), DemographicPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

package com.patientcard.observations

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class ObservationsPresenterImpl : BaseAbstractPresenter<ObservationsView>(), ObservationsPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

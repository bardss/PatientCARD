package com.patientcard.views.observations

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class ObservationsPresenterImpl : BaseAbstractPresenter<ObservationsView>(), ObservationsPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

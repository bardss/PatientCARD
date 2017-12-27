package com.patientcard.views.feverchart

import android.content.Intent
import com.patientcard.views.base.BaseAbstractPresenter

class FeverChartPresenterImpl : BaseAbstractPresenter<FeverChartView>(), FeverChartPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

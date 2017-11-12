package com.patientcard.feverchart

import android.content.Intent
import com.patientcard.base.BaseAbstractPresenter

class FeverChartPresenterImpl : BaseAbstractPresenter<FeverChartView>(), FeverChartPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}

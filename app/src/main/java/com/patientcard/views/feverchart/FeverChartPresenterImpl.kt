package com.patientcard.views.feverchart

import android.content.Intent
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.base.BaseAbstractPresenter

class FeverChartPresenterImpl : BaseAbstractPresenter<FeverChartView>(), FeverChartPresenter {

    private val model: FeverChartModel by lazy { FeverChartModel() }

    override fun initExtras(intent: Intent) {
        val feverCard = intent.getSerializableExtra(IntentKeys.FEVER_CARD) as ArrayList<FeverCardDTO>?
        model.feverCard = feverCard
    }

    override fun onViewAttached(view: FeverChartView?) {
        super.onViewAttached(view)
        view?.setupFeverGraph(model.feverCard)
    }
}

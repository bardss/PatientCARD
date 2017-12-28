package com.patientcard.views.feverchart

import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.base.BaseView

interface FeverChartView : BaseView {
    fun setupFeverGraph(feverCard: ArrayList<FeverCardDTO>?)
}

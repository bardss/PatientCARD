package com.patientcard.views.shortfever

import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.base.BaseView

interface ShortFeverView : BaseView {
    fun setFeverCard(feverCard: List<FeverCardDTO>)
}

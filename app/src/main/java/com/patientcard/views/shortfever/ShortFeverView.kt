package com.patientcard.views.shortfever

import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.base.BaseView

interface ShortFeverView : BaseView {
    fun setFeverCard(feverCard: List<FeverCardDTO>)
    fun setupButtons(feverCard: ArrayList<FeverCardDTO>, patientName: String?, patientId: String?)
    fun setPatientName(patientName: String?)
    fun setupEmptyView()
}

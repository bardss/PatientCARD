package com.patientcard.views.demographic

import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.views.base.BaseView

interface DemographicView : BaseView {
    fun fillFields(patient: PatientDTO)
    fun setupMenuButtons(qrCode: String?, name: String?)
}

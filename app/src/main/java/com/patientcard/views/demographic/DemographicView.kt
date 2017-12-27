package com.patientcard.views.demographic

import com.patientcard.views.base.BaseView
import com.patientcard.logic.model.transportobjects.PatientDTO

interface DemographicView : BaseView {
    fun fillFields(patient: PatientDTO)
}

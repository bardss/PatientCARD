package com.patientcard.demographic

import com.patientcard.base.BaseView
import com.patientcard.model.transportobjects.PatientDTO

interface DemographicView : BaseView {
    fun fillFields(patient: PatientDTO)
}

package com.patientcard.views.recommendations

import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.base.BaseView

interface AddObservationView : BaseView {
    fun navigateBack()
    fun setPatientName(patientName: String?)
    fun fillFields(observation: ObservationDTO?)
    fun setupDeleteIcon(observation: ObservationDTO?)
    fun setTitle(title: String?)
}

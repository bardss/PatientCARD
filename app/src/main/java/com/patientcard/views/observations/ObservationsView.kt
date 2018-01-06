package com.patientcard.views.observations

import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.base.BaseView

interface ObservationsView : BaseView {
    fun setObservationList(observations: List<ObservationDTO>)
    fun setupButtons(patientId: String?, patientName: String?)
    fun setPatientName(patientName: String?)
    fun clickEditObservation(observation: ObservationDTO?)
    fun openEditObservation(patientId: String?, patientName: String?, observation: ObservationDTO?)
}

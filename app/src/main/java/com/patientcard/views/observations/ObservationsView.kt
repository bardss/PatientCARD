package com.patientcard.views.observations

import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.base.BaseView

interface ObservationsView : BaseView {
    fun setObservationList(observations: List<ObservationDTO>)
}

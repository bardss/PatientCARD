package com.patientcard.views.observations

import android.content.Intent
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.base.BasePresenter

interface ObservationsPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun getObservations()
    fun openEditObservation(observation: ObservationDTO?)

}


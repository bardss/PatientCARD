package com.patientcard.views.addobservation

import android.content.Intent
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.base.BasePresenter

interface AddObservationPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun saveObservation(observation: ObservationDTO)
    fun deleteObservation()

}


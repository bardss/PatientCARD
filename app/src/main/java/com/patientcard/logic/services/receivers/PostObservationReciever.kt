package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.ObservationDTO

interface PostObservationReciever {

    fun onPostObservationSuccess(observations: ObservationDTO)

    fun onPostObservationError()
}
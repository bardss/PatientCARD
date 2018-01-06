package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.ObservationDTO

interface PutObservationReciever {

    fun onPutObservationSuccess(observations: ObservationDTO)

    fun onPutObservationError()
}
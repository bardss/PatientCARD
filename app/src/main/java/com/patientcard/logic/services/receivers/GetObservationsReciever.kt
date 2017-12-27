package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.ObservationDTO

interface GetObservationsReciever {

    fun onGetObservationsSuccess(observations: List<ObservationDTO>)

    fun onGetObservationError()
}
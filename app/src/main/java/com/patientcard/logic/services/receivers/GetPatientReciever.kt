package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.PatientDTO

interface GetPatientReciever {

    fun onGetPatientSuccess(patient: PatientDTO)

    fun onGetPatientError()
}
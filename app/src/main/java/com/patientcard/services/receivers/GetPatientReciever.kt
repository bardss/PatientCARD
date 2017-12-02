package com.patientcard.services.receivers

import com.patientcard.model.transportobjects.PatientDTO

interface GetPatientReciever {

    fun onGetPatientSuccess(patient: PatientDTO)

    fun onGetPatientError()
}
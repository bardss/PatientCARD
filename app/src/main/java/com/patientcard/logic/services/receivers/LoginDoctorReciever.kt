package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.TokenDTO

interface LoginDoctorReciever {

    fun onLoginDoctorSuccess(tokenDTO: TokenDTO)

    fun onLoginDoctorError(error: Throwable)
}
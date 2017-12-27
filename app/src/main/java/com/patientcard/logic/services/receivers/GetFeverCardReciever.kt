package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.FeverCardDTO

interface GetFeverCardReciever {

    fun onGetFeverCardSuccess(feverCard: List<FeverCardDTO>)

    fun onGetFeverCardError()
}
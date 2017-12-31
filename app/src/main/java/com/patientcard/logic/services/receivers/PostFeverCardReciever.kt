package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.FeverCardDTO

interface PostFeverCardReciever {

    fun onPostFeverCardSuccess(feverCard: FeverCardDTO)

    fun onPostFeverCardError()
}
package com.patientcard.logic.services

import com.patientcard.logic.services.api.ObservationApi
import com.patientcard.logic.services.api.PatientApi

object ServiceProvider {

    private var BASE_URL = "http://192.168.1.103:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)
    var observationsService: ObservationApi = ServiceFactory.createRetrofitService(ObservationApi::class.java, BASE_URL, false, false)

}
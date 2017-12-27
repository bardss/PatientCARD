package com.patientcard.logic.services

import com.patientcard.logic.services.api.PatientApi

object ServiceProvider {

    var BASE_URL = "http://192.168.1.103:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)

}
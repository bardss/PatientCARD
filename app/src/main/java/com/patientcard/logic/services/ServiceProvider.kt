package com.patientcard.logic.services

import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.services.api.ServiceFactory

object ServiceProvider {

    var BASE_URL = "http://10.0.2.2:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)

}
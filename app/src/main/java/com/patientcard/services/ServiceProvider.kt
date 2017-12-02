package com.patientcard.services

import com.patientcard.services.api.PatientApi
import com.patientcard.services.api.ServiceFactory

object ServiceProvider {

    var BASE_URL = "http://10.0.2.2:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)

}
package com.patientcard.logic.services

import com.patientcard.logic.services.api.FeverCardApi
import com.patientcard.logic.services.api.ObservationApi
import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.services.api.RecommendationApi

object ServiceProvider {

    private var BASE_URL = "http://192.168.1.103:8080"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, false)
    var observationsService: ObservationApi = ServiceFactory.createRetrofitService(ObservationApi::class.java, BASE_URL, false, false)
    var recommendationService: RecommendationApi = ServiceFactory.createRetrofitService(RecommendationApi::class.java, BASE_URL, false, false)
    var feverCardService: FeverCardApi = ServiceFactory.createRetrofitService(FeverCardApi::class.java, BASE_URL, false, false)

}
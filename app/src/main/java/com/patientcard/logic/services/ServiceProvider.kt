package com.patientcard.logic.services

import com.patientcard.logic.services.api.*

object ServiceProvider {

    private var BASE_URL = "http://192.168.43.99:8080"
    var AuthorizationHeader = "Basic cGF0aWVudENhcmQ6c2VjcmV0"

    var patientService: PatientApi = ServiceFactory.createRetrofitService(PatientApi::class.java, BASE_URL, false, true)
    var observationsService: ObservationApi = ServiceFactory.createRetrofitService(ObservationApi::class.java, BASE_URL, false, true)
    var recommendationService: RecommendationApi = ServiceFactory.createRetrofitService(RecommendationApi::class.java, BASE_URL, false, true)
    var feverCardService: FeverCardApi = ServiceFactory.createRetrofitService(FeverCardApi::class.java, BASE_URL, false, true)
    var oauthApi: OAuthApi = ServiceFactory.createRetrofitService(OAuthApi::class.java, BASE_URL, true, true)

}
package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RecommendationApi {

    @GET("/recommendation/findByPatientId")
    fun getRecommendations(
            @Query("patientId") patientId: String): Observable<List<RecommendationDTO>>
}

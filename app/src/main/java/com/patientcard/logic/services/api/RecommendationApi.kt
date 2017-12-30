package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface RecommendationApi {

    @GET("/recommendation/findByPatientId")
    fun getRecommendations(
            @Query("patientId") patientId: String): Observable<List<RecommendationDTO>>

    @POST("/recommendation/saveRecommendation")
    fun addRecommendation(
            @Body recommendation: RecommendationDTO): Observable<RecommendationDTO>
}

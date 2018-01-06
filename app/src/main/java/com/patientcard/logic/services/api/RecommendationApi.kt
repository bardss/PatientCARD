package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import retrofit2.http.*
import rx.Observable

interface RecommendationApi {

    @GET("/recommendation/findByPatientId")
    fun getRecommendations(
            @Query("patientId") patientId: String): Observable<List<RecommendationDTO>>

    @POST("/recommendation/saveRecommendation")
    fun addRecommendation(
            @Body recommendation: RecommendationDTO): Observable<RecommendationDTO>

    @PUT("/recommendation/editRecommendation")
    fun editRecommendation(
            @Body recommendation: RecommendationDTO): Observable<RecommendationDTO>

    @DELETE("/recommendation/deleteRecommendation")
    fun deleteRecommendation(
            @Query("recommendationId") recommendationId: String): Observable<Void>
}

package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.ObservationDTO
import retrofit2.http.*
import rx.Observable

interface ObservationApi {

    @GET("/observation/findByPatientId")
    fun getObservations(
            @Query("patientId") patientId: String): Observable<List<ObservationDTO>>

    @POST("/observation/saveObservation")
    fun addObservation(
            @Body observation: ObservationDTO): Observable<ObservationDTO>

    @PUT("/observation/editObservation")
    fun editObservation(
            @Body observation: ObservationDTO): Observable<ObservationDTO>

    @DELETE("/observation/deleteObservation")
    fun deleteObservation(
            @Query("observationId") observationId: String): Observable<Void>
}

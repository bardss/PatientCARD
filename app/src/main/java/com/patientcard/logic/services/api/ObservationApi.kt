package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.ObservationDTO
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ObservationApi {

    @GET("/observation/findByPatientId")
    fun getObservations(
            @Query("patientId") patientId: String): Observable<List<ObservationDTO>>
}

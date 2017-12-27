package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.FeverCardDTO
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface FeverCardApi {

    @GET("/fevercard/findByPatientId")
    fun getFeverCard(
            @Query("patientId") patientId: String): Observable<List<FeverCardDTO>>
}

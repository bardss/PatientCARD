package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.PatientDTO
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface PatientApi {

    @GET("/patients/findByQr")
    fun getPatient(
            @Query("qr") patientId: String): Observable<PatientDTO>
}

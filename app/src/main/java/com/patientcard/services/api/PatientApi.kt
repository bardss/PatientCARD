package com.patientcard.services.api

import com.patientcard.model.transportobjects.PatientDTO
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PatientApi {

    @GET("patients/{id}")
    fun getPatient(
            @Path("id") patientId: String): Observable<PatientDTO>
}

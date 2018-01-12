package com.patientcard.logic.services.api

import com.patientcard.logic.model.transportobjects.TokenDTO
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface OAuthApi {

    @POST("/oauth/token")
    fun loginDoctor(
            @Query("username") username: String,
            @Query("password") password: String,
            @Query("client_id") client_id: String,
            @Query("grant_type") grant_type: String
            ): Observable<TokenDTO>

}

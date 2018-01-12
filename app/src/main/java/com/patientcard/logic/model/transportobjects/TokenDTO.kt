package com.patientcard.logic.model.transportobjects

import java.io.Serializable

data class TokenDTO(
        var access_token: String? = null,
        var token_type: String? = null,
        var refresh_token: String? = null,
        var expires_in: Int? = null,
        var scope: String? = null,
        var jti: String? = null
) : Serializable
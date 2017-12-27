package com.patientcard.logic.model.transportobjects

import java.io.Serializable

data class PatientDTO(
        val id: Long? = null,
        val name: String = "",
        val surname: String = "",
        val patientCode: String = "",
        val qrCode: String? = null
) : Serializable
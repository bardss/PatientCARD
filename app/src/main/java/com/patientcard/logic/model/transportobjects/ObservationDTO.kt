package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDateTime

data class ObservationDTO(
        val id: Long = 0,
        val patientId: Long = 0,
        val employee: String = "",
        val dateTime: LocalDateTime? = null,
        val note: String = ""
)
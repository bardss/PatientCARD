package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDateTime

data class ObservationDTO(
        var id: Long? = 0,
        var patientId: Long? = 0,
        var employee: String = "",
        var dateTime: LocalDateTime? = null,
        var note: String = ""
)
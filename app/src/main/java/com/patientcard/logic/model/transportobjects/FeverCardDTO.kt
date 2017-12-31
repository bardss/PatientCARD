package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDate
import java.io.Serializable

data class FeverCardDTO(
        var id: Long? = 0,
        var patientId: Long? = 0,
        var date: LocalDate? = null,
        var timeOfDay: TimeOfDay = TimeOfDay.MORNING,
        var pulse: Int = 0,
        var temperature: Int = 0) : Serializable
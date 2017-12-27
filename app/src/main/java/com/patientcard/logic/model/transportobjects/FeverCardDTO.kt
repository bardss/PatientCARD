package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDate

data class FeverCardDTO(
        val id: Long = 0,
        val patientId: Long = 0,
        val date: LocalDate? = null,
        val timeOfDay: TimeOfDay = TimeOfDay.MORNING,
        val pulse: Int = 0,
        val temperature: Int = 0)
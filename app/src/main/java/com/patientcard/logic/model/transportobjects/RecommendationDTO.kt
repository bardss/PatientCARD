package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

data class RecommendationDTO(
        val id: Long = 0,
        val patientId: Long = 0,
        val date: LocalDate? = null,
        val description: String = "",
        val morning: LocalTime? = null,
        val noon: LocalTime? = null,
        val evening: LocalTime? = null,
        val night: LocalTime? = null)
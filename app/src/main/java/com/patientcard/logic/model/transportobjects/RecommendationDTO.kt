package com.patientcard.logic.model.transportobjects

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

data class RecommendationDTO(
        var id: Long? = 0,
        var patientId: Long? = 0,
        var date: LocalDate? = null,
        var description: String = "",
        var morning: LocalTime? = null,
        var noon: LocalTime? = null,
        var evening: LocalTime? = null,
        var night: LocalTime? = null)
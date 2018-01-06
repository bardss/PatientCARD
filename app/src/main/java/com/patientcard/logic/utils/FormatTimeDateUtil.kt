package com.patientcard.logic.utils

import com.patientcard.R
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object FormatTimeDateUtil {

    fun getFormattedDate(date: LocalDate?): String? {
        val dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return date?.format(dtf)
    }

    fun getFormattedTime(time: LocalTime?): String? {
        return if (time != null) {
            val dtf = DateTimeFormatter.ofPattern("HH:mm")
            time.format(dtf)
        } else {
            ResUtil.getString(R.string.time_placeholder)
        }
    }

    fun getFormattedDateTime(dateTime: LocalDateTime?): String? {
        val dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return dateTime?.format(dtf)
    }

}
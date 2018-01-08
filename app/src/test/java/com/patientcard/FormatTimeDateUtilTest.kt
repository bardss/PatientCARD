package com.patientcard

import com.patientcard.logic.utils.FormatTimeDateUtil
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

class FormatTimeDateUtilTest {

    @Test
    fun getFormattedDate_should_return_correct_string () {
        val localDate = LocalDate.of(1995, 7, 28)
        val localDateString = FormatTimeDateUtil.getFormattedDate(localDate)
        assertThat(localDateString, `is`("28.07.1995"))
    }

    @Test
    fun getFormattedDate_should_return_empty_string () {
        val localDateString = FormatTimeDateUtil.getFormattedDate(null)
        assertThat(localDateString, `is`(""))
    }

    @Test
    fun getFormattedDateTime_should_return_correct_string () {
        val localDateTime = LocalDateTime.of(1995, 7, 28,15,0)
        val localDateTimeString = FormatTimeDateUtil.getFormattedDateTime(localDateTime)
        assertThat(localDateTimeString, `is`("28.07.1995 15:00"))
    }

    @Test
    fun getFormattedDateTime_should_return_empty_string () {
        val localDateTimeString = FormatTimeDateUtil.getFormattedDateTime(null)
        assertThat(localDateTimeString, `is`(""))
    }

    @Test
    fun getFormattedTime_should_return_correct_string () {
        val localTime = LocalTime.of(15, 0)
        val localTimeString = FormatTimeDateUtil.getFormattedTime(localTime)
        assertThat(localTimeString, `is`("15:00"))
    }

}

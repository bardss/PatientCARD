package com.patientcard

import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.observations.ObservationsAdapter
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.threeten.bp.LocalDateTime


@RunWith(RobolectricTestRunner::class)
class ObservationsAdapterTest {

    var observationsAdapter: ObservationsAdapter? = null

    @Before
    fun initData() {
        observationsAdapter = ObservationsAdapter(RuntimeEnvironment.application)
    }

    @Test
    fun getCount_should_return_correct_0 () {
        assertThat(observationsAdapter?.itemCount, `is`(0))
    }

    @Test
    fun getCount_should_return_correct_2 () {
        val observationsList = getObservations(2)
        observationsAdapter?.setObservations(observationsList)
        assertThat(observationsAdapter?.itemCount, `is`(2))
    }

    @Test
    fun getCount_should_return_correct_5 () {
        val observationsList = getObservations(5)
        observationsAdapter?.setObservations(observationsList)
        assertThat(observationsAdapter?.itemCount, `is`(5))
    }

    @Test
    fun check_if_list_is_sorted_from_newest_to_oldest () {
        val observation2000year = ObservationDTO(1, 122075, "Jan Kowalski", LocalDateTime.of(2000, 7, 28,15,0), "Notatka 1")
        val observation1996year = ObservationDTO(2, 122075, "Marian Kowalski", LocalDateTime.of(1996, 7, 28,15,0), "Notatka 2")
        val observation1993year = ObservationDTO(3, 122075, "Janusz Kowalski", LocalDateTime.of(1993, 7, 28,15,0), "Notatka 3")
        val observation1998year = ObservationDTO(4, 122075, "Jarosław Kowalski", LocalDateTime.of(1998, 7, 28,15,0), "Notatka 4")

        val observations = ArrayList<ObservationDTO>()
        observations.add(observation2000year)
        observations.add(observation1996year)
        observations.add(observation1993year)
        observations.add(observation1998year)

        observationsAdapter?.setObservations(observations)

        assertThat(observationsAdapter?.getListItem(0), `is`(observation2000year))
        assertThat(observationsAdapter?.getListItem(1), `is`(observation1998year))
        assertThat(observationsAdapter?.getListItem(2), `is`(observation1996year))
        assertThat(observationsAdapter?.getListItem(3), `is`(observation1993year))
}

    private fun getObservations(count: Int): ArrayList<ObservationDTO> {
        val observations = ArrayList<ObservationDTO>()
        for (i in 1..count) {
            observations.add(ObservationDTO(1, 122075, "Jan Kowalski", LocalDateTime.of(1995, 7, 28,15,0), "Notatka 1"))
        }
        return observations
    }

}

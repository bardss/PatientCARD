package com.patientcard

import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.model.transportobjects.TimeOfDay
import com.patientcard.views.shortfever.ShortFeverAdapter
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.threeten.bp.LocalDate


@RunWith(RobolectricTestRunner::class)
class FeverCardAdapterTest {

    var feverCardAdapter: ShortFeverAdapter? = null

    @Before
    fun initData() {
        feverCardAdapter = ShortFeverAdapter(1)
    }

    @Test
    fun getCount_should_return_correct_0() {
        assertThat(feverCardAdapter?.itemCount, `is`(0))
    }

    @Test
    fun getCount_should_return_correct_3() {
        val feverCardList = getFeverCardList(3)
        feverCardAdapter?.setFeverCard(feverCardList)
        assertThat(feverCardAdapter?.itemCount, `is`(3))
    }

    @Test
    fun getCount_should_return_correct_6() {
        val feverCardList = getFeverCardList(6)
        feverCardAdapter?.setFeverCard(feverCardList)
        assertThat(feverCardAdapter?.itemCount, `is`(6))
    }

    @Test
    fun getCount_should_return_correct_10() {
        val feverCardList = getFeverCardList(10)
        feverCardAdapter?.setFeverCard(feverCardList)
        assertThat(feverCardAdapter?.itemCount, `is`(6))
    }

    @Test
    fun check_if_list_is_sorted_from_newest_to_oldest() {
        val feverCard28morning = FeverCardDTO(1, 1, LocalDate.of(1995, 7, 28), TimeOfDay.MORNING, 120, 36)
        val feverCard28evening = FeverCardDTO(2, 1, LocalDate.of(1995, 7, 28), TimeOfDay.EVENING, 121, 37)
        val feverCard29morning = FeverCardDTO(3, 1, LocalDate.of(1995, 7, 29), TimeOfDay.MORNING, 122, 38)
        val feverCard29evening = FeverCardDTO(4, 1, LocalDate.of(1995, 7, 29), TimeOfDay.EVENING, 123, 39)
        val feverCard30morning = FeverCardDTO(5, 1, LocalDate.of(1995, 7, 30), TimeOfDay.MORNING, 124, 40)
        val feverCard30evening = FeverCardDTO(6, 1, LocalDate.of(1995, 7, 30), TimeOfDay.EVENING, 125, 41)

        val feverCardList = ArrayList<FeverCardDTO>()
        feverCardList.add(feverCard30evening)
        feverCardList.add(feverCard28morning)
        feverCardList.add(feverCard29evening)
        feverCardList.add(feverCard29morning)
        feverCardList.add(feverCard30morning)
        feverCardList.add(feverCard28evening)

        feverCardAdapter?.setFeverCard(feverCardList)

        assertThat(feverCardAdapter?.getListItem(0), `is`(feverCard30evening))
        assertThat(feverCardAdapter?.getListItem(1), `is`(feverCard30morning))
        assertThat(feverCardAdapter?.getListItem(2), `is`(feverCard29evening))
        assertThat(feverCardAdapter?.getListItem(3), `is`(feverCard29morning))
        assertThat(feverCardAdapter?.getListItem(4), `is`(feverCard28evening))
        assertThat(feverCardAdapter?.getListItem(5), `is`(feverCard28morning))
    }

    private fun getFeverCardList(count: Int): ArrayList<FeverCardDTO> {
        val feverCardList = ArrayList<FeverCardDTO>()
        for (i in 1..count) {
            feverCardList.add(FeverCardDTO(1, 1, LocalDate.now(), TimeOfDay.MORNING, 120, 36))
        }
        return feverCardList
    }

}

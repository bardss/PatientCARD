package com.patientcard

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.recommendations.RecommendationsAdapter
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


@RunWith(RobolectricTestRunner::class)
class RecommendationsAdapterTest {

    var recommendationsAdapter: RecommendationsAdapter? = null

    @Before
    fun initData() {
        recommendationsAdapter = RecommendationsAdapter(RuntimeEnvironment.application)
    }

    @Test
    fun getCount_should_return_correct_0() {
        assertThat(recommendationsAdapter?.itemCount, `is`(0))
    }

    @Test
    fun getCount_should_return_correct_2() {
        val recommendationsList = getRecommendations(2)
        recommendationsAdapter?.setRecommendations(recommendationsList)
        assertThat(recommendationsAdapter?.itemCount, `is`(2))
    }

    @Test
    fun getCount_should_return_correct_5() {
        val recommendationsList = getRecommendations(5)
        recommendationsAdapter?.setRecommendations(recommendationsList)
        assertThat(recommendationsAdapter?.itemCount, `is`(5))
    }

    @Test
    fun check_if_list_is_sorted_from_newest_to_oldest() {
        val recommendation2000year = RecommendationDTO(1, 1, LocalDate.of(2000, 1,1), "", null, null, null, null)
        val recommendation1996year = RecommendationDTO(2, 1, LocalDate.of(1996, 1,1), "", null, null, null, null)
        val recommendation1993year = RecommendationDTO(3, 1, LocalDate.of(1993, 1,1), "", null, null, null, null)
        val recommendation1998year = RecommendationDTO(4, 1, LocalDate.of(1998, 1,1), "", null, null, null, null)

        val recommendations = ArrayList<RecommendationDTO>()
        recommendations.add(recommendation2000year)
        recommendations.add(recommendation1996year)
        recommendations.add(recommendation1993year)
        recommendations.add(recommendation1998year)

        recommendationsAdapter?.setRecommendations(recommendations)

        assertThat(recommendationsAdapter?.getListItem(0), `is`(recommendation2000year))
        assertThat(recommendationsAdapter?.getListItem(1), `is`(recommendation1998year))
        assertThat(recommendationsAdapter?.getListItem(2), `is`(recommendation1996year))
        assertThat(recommendationsAdapter?.getListItem(3), `is`(recommendation1993year))
    }

    private fun getRecommendations(count: Int): ArrayList<RecommendationDTO> {
        val recommendations = ArrayList<RecommendationDTO>()
        for (i in 1..count) {
            recommendations.add(RecommendationDTO(1, 1, LocalDate.now(), "Notatka 1", LocalTime.parse("08:00"), LocalTime.parse("12:00"), LocalTime.parse("18:00"), LocalTime.parse("22:00")))
        }
        return recommendations
    }

}

package com.patientcard.views.addrecommendation


import UITestData
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.services.ServiceProvider
import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.services.api.RecommendationApi
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.qrrcode.QRCodeActivity
import com.patientcard.views.testutils.RecyclerViewMatcher
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.threeten.bp.LocalDate
import rx.Observable

@RunWith(AndroidJUnit4::class)
class AddRecommendationActivityTest {

    @Rule @JvmField
    var mActivityTestRule: ActivityTestRule<QRCodeActivity> = ActivityTestRule(QRCodeActivity::class.java, true, false)

    @Mock
    private val patientApi = Mockito.mock(PatientApi::class.java)

    @Mock
    private val recommendationApi = Mockito.mock(RecommendationApi::class.java)

    var patient: PatientDTO? = null
    var recommendations: ArrayList<RecommendationDTO>? = null

    var recyclerViewMatcher: RecyclerViewMatcher? = null

    @Before
    fun initTestData() {
        patient = UITestData.getPatient()
        recommendations = UITestData.getRecommendations()

        `when`(patientApi.getPatient("122075")).thenReturn(Observable.just(patient))
        ServiceProvider.patientService = patientApi

        `when`(recommendationApi.getRecommendations("1")).thenReturn(Observable.just(recommendations))
        ServiceProvider.recommendationService = recommendationApi

        recyclerViewMatcher = RecyclerViewMatcher(R.id.recommendationsRecyclerView)

        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }

    @Test
    fun addRecommendationActivityTest() {
        moveToAddRecommendations()

        closeKeyboard()

        onView(withId(R.id.nameTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.name + " " + patient?.surname)))

        assertHasDrawable(R.id.pageIconImageView, R.drawable.rounded_recommendations_icon)

        onView(withId(R.id.pageTitleTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.add_recommendation))))

        onView(withId(R.id.checkFab))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.recommendationDateTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(getLabelWithDateString())))

        onView(withId(R.id.drugLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.drug))))

        onView(withId(R.id.drugEditText))
                .check(matches(isDisplayed()))
                .check(matches(withHint(ResUtil.getString(R.string.fill_data_with_dots))))

        checkTimeOfADayContent(R.id.morningTimeTextView, R.id.morningTimeEditText, R.string.morning)
        checkTimeOfADayContent(R.id.noonTimeTextView, R.id.noonTimeEditText, R.string.noon)
        checkTimeOfADayContent(R.id.eveningTimeTextView, R.id.eveningTimeEditText, R.string.evening)
        checkTimeOfADayContent(R.id.nightTimeTextView, R.id.nightTimeEditText, R.string.night)

    }

    private fun checkTimeOfADayContent(labelId: Int, editTextId: Int, timeOfDayId: Int) {
        onView(withId(labelId))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(timeOfDayId))))

        onView(withId(editTextId))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.time_placeholder))))
    }

    private fun getLabelWithDateString(): String {
        return ResUtil.getString(R.string.recommendation) + " " + FormatTimeDateUtil.getFormattedDate(LocalDate.now())
    }

    private fun moveToAddRecommendations() {
        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))
                .perform(typeText(UITestData.getQRCode()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click())

        onView(withId(R.id.recommendationsMenuFrameLayout))
                .perform(click())

        onView(withId(R.id.addFab))
                .perform(click())
    }

}

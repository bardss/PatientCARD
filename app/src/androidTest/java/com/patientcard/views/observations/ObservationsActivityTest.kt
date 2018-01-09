package com.patientcard.views.observations


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
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.ServiceProvider
import com.patientcard.logic.services.api.ObservationApi
import com.patientcard.logic.services.api.PatientApi
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
import rx.Observable

@RunWith(AndroidJUnit4::class)
class ObservationsActivityTest {

    @Rule @JvmField
    var mActivityTestRule: ActivityTestRule<QRCodeActivity> = ActivityTestRule(QRCodeActivity::class.java, true, false)

    @Mock
    private val patientApi = Mockito.mock(PatientApi::class.java)

    @Mock
    private val observationsApi = Mockito.mock(ObservationApi::class.java)

    var patient: PatientDTO? = null
    var observations: ArrayList<ObservationDTO>? = null

    var recyclerViewMatcher: RecyclerViewMatcher? = null

    @Before
    fun initTestData() {
        patient = UITestData.getPatient()
        observations = UITestData.getObservations()

        `when`(patientApi.getPatient("122075")).thenReturn(Observable.just(patient))
        ServiceProvider.patientService = patientApi

        `when`(observationsApi.getObservations("1")).thenReturn(Observable.just(observations))
        ServiceProvider.observationsService = observationsApi

        recyclerViewMatcher = RecyclerViewMatcher(R.id.observationsRecyclerView)

        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }

    @Test
    fun observationsActivityTest() {
        moveToObservations()

        closeKeyboard()

        onView(withId(R.id.nameTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.name + " " + patient?.surname)))

        assertHasDrawable(R.id.pageIconImageView, R.drawable.rounded_observations_icon)

        onView(withId(R.id.pageTitleTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.observation_card))))

        onView(withId(R.id.addFab))
                .check(matches(isCompletelyDisplayed()))

        onView(recyclerViewMatcher?.atPosition(0))
                .check(matches(hasDescendant(withText(observations?.get(0)?.employee))))

        onView(recyclerViewMatcher?.atPosition(0))
                .check(matches(hasDescendant(withText(observations?.get(0)?.note))))

        onView(recyclerViewMatcher?.atPosition(1))
                .check(matches(hasDescendant(withText(observations?.get(1)?.employee))))

        onView(recyclerViewMatcher?.atPosition(1))
                .check(matches(hasDescendant(withText(observations?.get(1)?.note))))

    }

    private fun moveToObservations() {
        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))
                .perform(typeText(UITestData.getQRCode()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click())

        onView(withId(R.id.observationsMenuFrameLayout))
                .perform(click())
    }

}

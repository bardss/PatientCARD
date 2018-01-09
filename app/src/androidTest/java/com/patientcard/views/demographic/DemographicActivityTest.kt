package com.patientcard.views.demographic


import UITestData
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.ServiceProvider
import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.qrrcode.QRCodeActivity
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import rx.Observable

@RunWith(AndroidJUnit4::class)
class DemographicActivityTest {

    @Rule @JvmField
    var mActivityTestRule: ActivityTestRule<QRCodeActivity> = ActivityTestRule(QRCodeActivity::class.java, true, false)

    @Mock
    private val patientApi = Mockito.mock(PatientApi::class.java)

    var patient: PatientDTO? = null

    @Before
    fun initTestData() {
        patient = UITestData.getPatient()

        `when`(patientApi.getPatient("122075")).thenReturn(Observable.just(patient))
        ServiceProvider.patientService = patientApi

        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }

    @Test
    fun demographicActivityTest() {
        moveToDemographic()

        onView(withId(R.id.pageIconImageView))
                .check(matches(ViewMatchers.isCompletelyDisplayed()))

        onView(withId(R.id.nameTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.name + " " + patient?.surname)))

        onView(withId(R.id.patientCodeTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.patientCode)))

        assertHasDrawable(R.id.pageIconImageView, R.drawable.patient_avatar)

        assertHasDrawable(R.id.feverCardImageView, R.drawable.fever_icon)

        onView(withId(R.id.feverCardTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.fever_card))))

        assertHasDrawable(R.id.recommendationImageView, R.drawable.recommendations_icon)

        onView(withId(R.id.recommendationTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.recommendation_card))))

        assertHasDrawable(R.id.observationImageView, R.drawable.observations_icon)

        onView(withId(R.id.observationTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.observation_card))))


    }

    private fun moveToDemographic() {
        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))
                .perform(typeText(UITestData.getQRCode()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click())
    }

}

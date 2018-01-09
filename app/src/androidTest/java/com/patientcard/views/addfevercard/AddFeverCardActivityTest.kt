package com.patientcard.views.addfevercard


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
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.ServiceProvider
import com.patientcard.logic.services.api.FeverCardApi
import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.qrrcode.QRCodeActivity
import com.patientcard.views.testutils.RecyclerViewMatcher
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertTextColorIs
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
class AddFeverCardActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<QRCodeActivity> = ActivityTestRule(QRCodeActivity::class.java, true, false)

    @Mock
    private val patientApi = Mockito.mock(PatientApi::class.java)

    @Mock
    private val feverCardApi = Mockito.mock(FeverCardApi::class.java)

    var patient: PatientDTO? = null
    var feverCardList: ArrayList<FeverCardDTO>? = null

    var recyclerViewMatcher: RecyclerViewMatcher? = null

    @Before
    fun initTestData() {
        patient = UITestData.getPatient()
        feverCardList = UITestData.getFeverCardList()

        `when`(patientApi.getPatient("122075")).thenReturn(Observable.just(patient))
        ServiceProvider.patientService = patientApi

        `when`(feverCardApi.getFeverCard("1")).thenReturn(Observable.just(feverCardList))
        ServiceProvider.feverCardService = feverCardApi

        recyclerViewMatcher = RecyclerViewMatcher(R.id.shortFeverRecyclerView)

        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }


    @Test
    fun addFeverCardActivityTest() {
        moveToAddFeverCard()

        closeKeyboard()

        onView(withId(R.id.nameTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.name + " " + patient?.surname)))

        assertHasDrawable(R.id.pageIconImageView, R.drawable.rounded_fever_icon)

        onView(withId(R.id.pageTitleTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.add_examination))))

        onView(withId(R.id.checkFab))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.dateTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(getLabelWithDateString())))

        onView(withId(R.id.pulseLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.pulse))))

        assertTextColorIs(R.id.pulseLabelTextView, R.color.colorPulse)

        onView(withId(R.id.temperatureLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.temperature))))

        assertTextColorIs(R.id.temperatureLabelTextView, R.color.colorTemperature)

        onView(withId(R.id.pulseEditText))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.temperatureEditText))
                .check(matches(isCompletelyDisplayed()))

    }

    private fun getLabelWithDateString(): String {
        return ResUtil.getString(R.string.examination) + " " + FormatTimeDateUtil.getFormattedDate(LocalDate.now())
    }

    private fun moveToAddFeverCard() {
        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))
                .perform(typeText(UITestData.getQRCode()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click())

        onView(withId(R.id.feverMenuFrameLayout))
                .perform(click())

        onView(withId(R.id.addFab))
                .perform(click())
    }

}

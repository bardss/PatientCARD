package com.patientcard.views.shortfever


import MockData
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
import com.patientcard.logic.model.transportobjects.TimeOfDay
import com.patientcard.logic.services.ServiceProvider
import com.patientcard.logic.services.api.FeverCardApi
import com.patientcard.logic.services.api.PatientApi
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.qrrcode.QRCodeActivity
import com.patientcard.views.testutils.RecyclerViewMatcher
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
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
class ShortFeverActivityTest {

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
        patient = MockData.getPatient()
        feverCardList = MockData.getFeverCardList()

        `when`(patientApi.getPatient("122075")).thenReturn(Observable.just(patient))
        ServiceProvider.patientService = patientApi

        `when`(feverCardApi.getFeverCard("1")).thenReturn(Observable.just(feverCardList))
        ServiceProvider.feverCardService = feverCardApi

        recyclerViewMatcher = RecyclerViewMatcher(R.id.shortFeverRecyclerView)

        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }

    @Test
    fun shortFeverActivityTest() {
        moveToObservations()

        onView(withId(R.id.nameTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(patient?.name + " " + patient?.surname)))

        assertHasDrawable(R.id.pageIconImageView, R.drawable.rounded_fever_icon)

        onView(withId(R.id.pageTitleTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.fever_card))))

        onView(withId(R.id.addFab))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.dateLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.date))))

        onView(withId(R.id.pulseLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.pulse))))

        onView(withId(R.id.temperatureLabelTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.temperature))))

        sleep(3000)

        checkFeverCardItem(0)
        checkFeverCardItem(1)
        checkFeverCardItem(2)
        checkFeverCardItem(3)
        checkFeverCardItem(4)
        checkFeverCardItem(5)

    }

    private fun checkFeverCardItem(position: Int) {
        val item = feverCardList?.get(position)

        onView(recyclerViewMatcher?.atPosition(position))
                .check(matches(hasDescendant(withText(getDateAndTimeString(item?.date, item?.timeOfDay)))))

        onView(recyclerViewMatcher?.atPosition(position))
                .check(matches(hasDescendant(withText(item?.pulse.toString()))))

        onView(recyclerViewMatcher?.atPosition(position))
                .check(matches(hasDescendant(withText(item?.temperature.toString()))))
    }

    private fun getDateAndTimeString(date: LocalDate?, time: TimeOfDay?): String {
        return FormatTimeDateUtil.getFormattedDate(date) + "\n" + time?.stringValue
    }

    private fun moveToObservations() {
        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))
                .perform(typeText(MockData.getQRCode()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click())

        onView(withId(R.id.feverMenuFrameLayout))
                .perform(click())
    }

}

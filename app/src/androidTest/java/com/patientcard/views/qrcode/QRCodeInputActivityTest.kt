package com.patientcard.views.qrcode


import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.qrrcode.QRCodeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QRCodeInputActivityTest {

    @Rule @JvmField
    var mActivityTestRule: ActivityTestRule<QRCodeActivity> = ActivityTestRule(QRCodeActivity::class.java, true, false)

    @Before
    fun initTestData() {
        mActivityTestRule.launchActivity(Intent()
                .putExtra(IntentKeys.CAMERA_PERMISSION, false))
    }

    @Test
    fun qrCodeActivityTest() {

        onView(withId(R.id.backgroundView))
                .check(matches(isDisplayed()))

        onView(withId(R.id.qrInputTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.enter_patient_qr_code))))

        onView(withId(R.id.qrCodeEditText))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.openButton))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(isClickable()))

        onView(withId(R.id.openTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.open))))

    }

}

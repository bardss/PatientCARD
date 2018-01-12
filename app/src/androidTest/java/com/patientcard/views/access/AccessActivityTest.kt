package com.patientcard.views.access


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.patientcard.R
import com.patientcard.logic.utils.ResUtil
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccessActivityTest {

    @Rule @JvmField
    var mActivityTestRule = ActivityTestRule(AccessActivity::class.java)

    @Test
    fun accessActivityTest() {

        onView(withId(R.id.welcomeTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.hello))))

        onView(withId(R.id.emailTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.id_email))))

        onView(withId(R.id.emailImageView))
                .check(matches(isCompletelyDisplayed()))

        assertHasDrawable(R.id.emailImageView, R.drawable.login)

        onView(withId(R.id.loginEditText))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.passwordTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.password))))

        onView(withId(R.id.passwordImageView))
                .check(matches(isCompletelyDisplayed()))

        assertHasDrawable(R.id.passwordImageView, R.drawable.password)

        onView(withId(R.id.passwordEditText))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.loginButton))
                .check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.loginTextView))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withText(ResUtil.getString(R.string.login))))
    }

}

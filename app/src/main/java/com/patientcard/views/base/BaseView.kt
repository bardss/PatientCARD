package com.patientcard.views.base

import android.content.Context

interface BaseView {

    val activityContext: Context

    fun startProgressDialog(message: String?)

    fun stopProgressDialog()

    fun performOnBackPressed()

}

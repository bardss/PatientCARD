package com.example.jakubaniola.patientcard.base

import android.content.Context

interface BaseView {

    val activityContext: Context

    fun startProgressDialog(message: String)

    fun stopProgressDialog()

}

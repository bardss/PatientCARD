package com.patientcard.views.access

import com.patientcard.views.base.BaseView

interface AccessView : BaseView {
    fun checkPermission()
    fun fillLogin(login: String)
    fun setInputErrors()
}

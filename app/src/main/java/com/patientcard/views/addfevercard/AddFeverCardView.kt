package com.patientcard.views.addfevercard

import com.patientcard.views.base.BaseView

interface AddFeverCardView : BaseView {
    fun setPatientName(patientName: String?)
    fun setupButton()

}

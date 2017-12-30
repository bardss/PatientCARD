package com.patientcard.views.recommendations

import com.patientcard.views.base.BaseView

interface AddObservationView : BaseView {
    fun navigateBack()
    fun setPatientName(patientName: String?)
}

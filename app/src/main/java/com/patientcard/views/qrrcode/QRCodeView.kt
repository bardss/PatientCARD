package com.patientcard.views.splash

import com.patientcard.views.base.BaseView

interface QRCodeView : BaseView {
    fun setupQRReader()
    fun setupNoPermission()
}

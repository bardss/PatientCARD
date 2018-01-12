package com.patientcard.views.splash

import android.content.Intent
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.views.base.BaseAbstractPresenter

class QRCodePresenterImpl : BaseAbstractPresenter<QRCodeView>(), QRCodePresenter {

    private val model: QRCodeModel by lazy { QRCodeModel() }

    override fun initExtras(intent: Intent) {
        var cameraPermission = intent.getBooleanExtra(IntentKeys.CAMERA_PERMISSION, false)
        model.cameraPermission = cameraPermission
    }

    override fun onViewAttached(view: QRCodeView?) {
        super.onViewAttached(view)
        if (model.isInitializing) {
            setupViews()
            model.isInitializing = false
        }
    }

    private fun setupViews() {
        if (model.cameraPermission) view?.setupQRReader() else view?.setupNoPermission()
    }

}

package com.patientcard.views.splash

import android.content.Intent
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.views.base.BaseAbstractPresenter

class QRCodePresenterImpl : BaseAbstractPresenter<QRCodeView>(), QRCodePresenter {

    private val presentationModel: QRCodeModel by lazy { QRCodeModel() }

    override fun initExtras(intent: Intent) {
        var cameraPermission = intent.getBooleanExtra(IntentKeys.CAMERA_PERMISSION, false)
        presentationModel.cameraPermission = cameraPermission
    }

    override fun onViewAttached(view: QRCodeView?) {
        super.onViewAttached(view)
        setupViews()
    }

    private fun setupViews() {
        if (presentationModel.cameraPermission) view?.setupQRReader() else view?.setupNoPermission()
    }

}

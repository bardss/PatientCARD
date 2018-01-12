package com.patientcard.views.qrrcode

import android.content.Intent
import android.view.View
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.demographic.DemographicActivity
import com.patientcard.views.splash.QRCodePresenter
import com.patientcard.views.splash.QRCodePresenterImpl
import com.patientcard.views.splash.QRCodeView
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import github.nisrulz.qreader.QRDataListener
import github.nisrulz.qreader.QREader
import kotlinx.android.synthetic.main.activity_qr_reader.*
import kotlinx.android.synthetic.main.dialog_qr.*

@ActivityView(layout = R.layout.activity_qr_reader, presenter = QRCodePresenterImpl::class)
class QRCodeActivity : BaseActivity(), QRCodeView {

    private var qrReader : QREader? = null

    @Presenter
    lateinit var presenter: QRCodePresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        qrReader?.initAndStart(qrReaderSurfaceView)
    }

    override fun onPause() {
        super.onPause()
        qrReader?.releaseAndCleanup()
    }

    override fun setupQRReader() {
        qrCodeDialogLayout.visibility = View.GONE
        qrReader = QREader.Builder(this, qrReaderSurfaceView, QRDataListener { data ->
            openPatientDemographic(data)
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(qrReaderSurfaceView!!.height)
                .width(qrReaderSurfaceView.width)
                .build()
        qrReader?.initAndStart(qrReaderSurfaceView)
    }

    override fun setupNoPermission() {
        qrCodeDialogLayout.visibility = View.VISIBLE
        openButton.setOnClickListener { openPatientDemographic(qrCodeEditText.text.toString()) }
    }

    private fun openPatientDemographic(qrCode: String) {
        startActivity(Intent(this, DemographicActivity::class.java)
                .putExtra(IntentKeys.QR_CODE, qrCode))
        finish()
    }
}

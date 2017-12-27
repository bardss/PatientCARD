package com.patientcard.views.qrreader

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.demographic.DemographicActivity
import com.patientcard.views.splash.QRReaderPresenter
import com.patientcard.views.splash.QRReaderPresenterImpl
import com.patientcard.views.splash.QRReaderView
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import github.nisrulz.qreader.QRDataListener
import github.nisrulz.qreader.QREader
import kotlinx.android.synthetic.main.activity_qr_reader.*

@ActivityView(layout = R.layout.activity_qr_reader, presenter = QRReaderPresenterImpl::class)
class QRReaderActivity : BaseActivity(), QRReaderView {

    private var qrReader : QREader? = null

    @Presenter
    lateinit var presenter: QRReaderPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupQRReader()
    }

    override fun onResume() {
        super.onResume()
        qrReader?.initAndStart(qrReaderSurfaceView)
    }

    override fun onPause() {
        super.onPause()
        qrReader?.releaseAndCleanup()
    }

    private fun setupQRReader() {
        qrReader = QREader.Builder(this, qrReaderSurfaceView, QRDataListener { data ->
            openPatientDemographic(data)
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(qrReaderSurfaceView!!.height)
                .width(qrReaderSurfaceView.width)
                .build()
        qrReader?.initAndStart(qrReaderSurfaceView)
    }

    private fun openPatientDemographic(qrCode: String) {
        startActivity(Intent(this, DemographicActivity::class.java)
                .putExtra(IntentKeys.QR_CODE, qrCode))
    }
}

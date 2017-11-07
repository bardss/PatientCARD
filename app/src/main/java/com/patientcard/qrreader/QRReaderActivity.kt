package com.patientcard.qrreader

import android.content.Intent
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.demographic.DemographicActivity
import com.patientcard.splash.QRReaderPresenter
import com.patientcard.splash.QRReaderPresenterImpl
import com.patientcard.splash.QRReaderView
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.yanzhenjie.permission.PermissionListener
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import github.nisrulz.qreader.QRDataListener
import github.nisrulz.qreader.QREader
import kotlinx.android.synthetic.main.activity_qr_reader.*
import timber.log.Timber

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
        checkPermission()
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
            Timber.e("QREader", "Value : " + data)
            openPatientDemographic();
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(qrReaderSurfaceView!!.height)
                .width(qrReaderSurfaceView.width)
                .build()
        qrReader?.initAndStart(qrReaderSurfaceView)
    }

    private fun checkPermission(){
        AndPermission
                .with(this)
                .requestCode(0)
                .permission(Permission.CAMERA)
                .callback(object : PermissionListener {
                    override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                        setupQRReader()
                    }

                    override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                    }
                })
                .start()

    }

    private fun openPatientDemographic() {
        startActivity(Intent(this, DemographicActivity::class.java))
    }
}

package com.example.jakubaniola.patientcard.qrreader

import android.content.Intent
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.demographic.DemographicActivity
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.splash.QRReaderPresenter
import com.example.jakubaniola.patientcard.splash.QRReaderPresenterImpl
import com.example.jakubaniola.patientcard.splash.QRReaderView
import com.rsqtechnologies.rsqphysio.base.BaseActivity
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
        if (qrReader != null) {
            qrReader!!.initAndStart(qr_reader_surface_view)
        }
    }

    override fun onPause() {
        super.onPause()
        if (qrReader != null) {
            qrReader!!.releaseAndCleanup()
        }
    }

    private fun setupQRReader() {
        qrReader = QREader.Builder(this, qr_reader_surface_view, QRDataListener { data ->
            Timber.e("QREader", "Value : " + data)
            openPatientDemographic();
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(qr_reader_surface_view!!.height)
                .width(qr_reader_surface_view.width)
                .build()
        qrReader!!.initAndStart(qr_reader_surface_view)
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

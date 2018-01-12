package com.patientcard.views.demographic

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.observations.ObservationsActivity
import com.patientcard.views.qrrcode.QRCodeActivity
import com.patientcard.views.recommendations.RecommendationsActivity
import com.patientcard.views.shortfever.ShortFeverActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.yanzhenjie.permission.PermissionListener
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_demographic.*

@ActivityView(layout = R.layout.activity_demographic, presenter = DemographicPresenterImpl::class)
class DemographicActivity : BaseActivity(), DemographicView {

    @Presenter
    lateinit var presenter: DemographicPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun fillFields(patient: PatientDTO) {
        nameTextView.text = patient.name + " " + patient.surname
        patientCodeTextView.text = patient.patientCode
    }

    override fun setupMenuButtons(qrCode: String?, name: String?) {
        feverMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, ShortFeverActivity::class.java)
                    .putExtra(IntentKeys.PATIENT_ID, qrCode)
                    .putExtra(IntentKeys.PATIENT_NAME, name))
        }
        recommendationsMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, RecommendationsActivity::class.java)
                    .putExtra(IntentKeys.PATIENT_ID, qrCode)
                    .putExtra(IntentKeys.PATIENT_NAME, name))
        }
        observationsMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, ObservationsActivity::class.java)
                    .putExtra(IntentKeys.PATIENT_ID, qrCode)
                    .putExtra(IntentKeys.PATIENT_NAME, name))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        checkPermissionAndOpenQReader()
    }

    private fun openQRReaderActivity(cameraPermission: Boolean) {
        startActivity(Intent(this, QRCodeActivity::class.java)
                .putExtra(IntentKeys.CAMERA_PERMISSION, cameraPermission))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun checkPermissionAndOpenQReader(){
        AndPermission
                .with(this)
                .requestCode(0)
                .permission(Permission.CAMERA)
                .callback(object : PermissionListener {
                    override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                        openQRReaderActivity(true)
                    }

                    override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                        openQRReaderActivity(false)
                    }
                })
                .start()

    }
}

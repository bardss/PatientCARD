package com.patientcard.views.demographic

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.GetPatientReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class DemographicPresenterImpl : BaseAbstractPresenter<DemographicView>(), DemographicPresenter, GetPatientReciever {

    private val model: DemographicModel by lazy { DemographicModel() }

    override fun initExtras(intent: Intent) {
        val qrCode: String? = intent.getSerializableExtra(IntentKeys.QR_CODE) as String?
        model.qrCode = qrCode
    }

    override fun onViewAttached(view: DemographicView?) {
        super.onViewAttached(view)
        getPatientDetails()
    }

    private fun getPatientDetails() {
        val qrCode: String? = model.qrCode
        if (qrCode != null) {
            view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
            ServiceManager.getPatient(this, qrCode)
        }
    }

    override fun onGetPatientError() {
        view?.stopProgressDialog()
        view?.performOnBackPressed()
    }

    override fun onGetPatientSuccess(patient: PatientDTO) {
        view?.stopProgressDialog()
        view?.fillFields(patient)
        view?.setupMenuButtons(patient.id?.toString(), patient.name + " " + patient.surname)
    }

}

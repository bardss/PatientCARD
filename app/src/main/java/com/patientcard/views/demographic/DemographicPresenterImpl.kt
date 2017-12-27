package com.patientcard.views.demographic

import android.content.Intent
import com.patientcard.R
import com.patientcard.views.base.BaseAbstractPresenter
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.GetPatientReciever
import com.patientcard.logic.utils.ResUtil

class DemographicPresenterImpl : BaseAbstractPresenter<DemographicView>(), DemographicPresenter, GetPatientReciever {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: DemographicView?) {
        super.onViewAttached(view)
        getPatientDetails()
    }

    private fun getPatientDetails() {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getPatient(this, "122075")
    }

    override fun onGetPatientError() {
        view?.stopProgressDialog()
    }

    override fun onGetPatientSuccess(patient: PatientDTO) {
        view?.stopProgressDialog()
        view?.fillFields(patient)
    }

}

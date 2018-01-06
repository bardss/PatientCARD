package com.patientcard.views.observations

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.GetObservationsReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class ObservationsPresenterImpl : BaseAbstractPresenter<ObservationsView>(), ObservationsPresenter, GetObservationsReciever {

    private val model: ObservationsModel by lazy { ObservationsModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        model.patientId = patientId
        model.patientName = patientName
    }

    override fun onViewAttached(view: ObservationsView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
        view?.setupButtons(model.patientId, model.patientName)
    }

    override fun getObservations() {
        val patientId: String? = model.patientId
        if (patientId != null) {
            view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
            ServiceManager.getObservations(this, patientId)
        }
    }

    override fun onGetObservationsSuccess(observations: List<ObservationDTO>) {
        view?.setObservationList(observations)
        view?.stopProgressDialog()
    }

    override fun onGetObservationError() {
        view?.stopProgressDialog()
    }

    override fun openEditObservation(observation: ObservationDTO?) {
        view?.openEditObservation(model.patientId, model.patientName, observation)
    }

}


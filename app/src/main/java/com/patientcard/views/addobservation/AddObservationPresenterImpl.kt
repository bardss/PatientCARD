package com.patientcard.views.addobservation

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.PostObservationReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter
import com.patientcard.views.recommendations.AddObservationView

class AddObservationPresenterImpl : BaseAbstractPresenter<AddObservationView>(), AddObservationPresenter, PostObservationReciever {

    private val presentationModel: AddObservationModel by lazy { AddObservationModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        presentationModel.patientId = patientId
    }

    override fun saveObservation(observation: ObservationDTO) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        observation.patientId = presentationModel.patientId?.toLong()
        ServiceManager.addObservations(this, observation)
    }

    override fun onPostObservationError() {
        view?.stopProgressDialog()
    }

    override fun onPostObservationSuccess(observations: ObservationDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

}

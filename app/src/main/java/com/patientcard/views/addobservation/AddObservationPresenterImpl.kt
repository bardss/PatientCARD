package com.patientcard.views.addobservation

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.DeleteObservationReciever
import com.patientcard.logic.services.receivers.PostObservationReciever
import com.patientcard.logic.services.receivers.PutObservationReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter
import com.patientcard.views.recommendations.AddObservationView
import org.threeten.bp.LocalDateTime

class AddObservationPresenterImpl : BaseAbstractPresenter<AddObservationView>(), AddObservationPresenter, PostObservationReciever, PutObservationReciever, DeleteObservationReciever {

    private val model: AddObservationModel by lazy { AddObservationModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        val observation: ObservationDTO? = intent.getSerializableExtra(IntentKeys.OBSERVATION) as ObservationDTO?
        model.patientId = patientId
        model.patientName = patientName
        model.observation = observation
    }

    override fun onViewAttached(view: AddObservationView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
        if (model.observation != null) {
            view?.fillFields(model.observation)
            view?.setupDeleteIcon(model.observation)
            view?.setTitle(ResUtil.getString(R.string.edit_observation))
        } else {
            view?.setTitle(ResUtil.getString(R.string.add_observation))
            view?.setObservationLabel()
        }
    }

    override fun saveObservation(observation: ObservationDTO) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        observation.patientId = model.patientId?.toLong()
        if (model.observation == null) {
            observation.dateTime = LocalDateTime.now()
            ServiceManager.addObservations(this, observation)
        } else {
            observation.id = model.observation?.id
            observation.dateTime = model.observation?.dateTime
            ServiceManager.editObservations(this, observation)
        }
    }

    override fun deleteObservation() {
        if (model.observation != null) {
            ServiceManager.deleteObservations(this, model.observation?.id.toString())
        }
    }

    override fun onPostObservationError() {
        view?.stopProgressDialog()
    }

    override fun onPostObservationSuccess(observations: ObservationDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

    override fun onPutObservationError() {
        view?.stopProgressDialog()
    }

    override fun onPutObservationSuccess(observations: ObservationDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

    override fun onDeleteObservationError() {
        view?.stopProgressDialog()
    }

    override fun onDeleteObservationSuccess() {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

}

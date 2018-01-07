package com.patientcard.views.addrecommendation

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.DeleteRecommendationReciever
import com.patientcard.logic.services.receivers.PostRecommendationReciever
import com.patientcard.logic.services.receivers.PutRecommendationReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class AddRecommendationPresenterImpl : BaseAbstractPresenter<AddRecommendationView>(), AddRecommendationPresenter, PostRecommendationReciever, PutRecommendationReciever, DeleteRecommendationReciever{

    private val model: AddRecommendationModel by lazy { AddRecommendationModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        val recommendation: RecommendationDTO? = intent.getSerializableExtra(IntentKeys.RECOMMENDATION) as RecommendationDTO?
        model.patientId = patientId
        model.patientName = patientName
        model.recommendation = recommendation
    }

    override fun onViewAttached(view: AddRecommendationView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
        if (model.recommendation != null) {
            view?.fillFields(model.recommendation)
            view?.setupDeleteIcon(model.recommendation)
            view?.setTitle(ResUtil.getString(R.string.edit_recommendation))
        } else {
            view?.setTitle(ResUtil.getString(R.string.add_recommendation))
            view?.setLabel()
        }
    }

    override fun saveRecommendation(recommendation: RecommendationDTO) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        recommendation.patientId = model.patientId?.toLong()
        if (model.recommendation == null) {
            recommendation.date = LocalDate.now()
            ServiceManager.addRecommendation(this, recommendation)
        } else {
            recommendation.id = model.recommendation?.id
            recommendation.date = model.recommendation?.date
            ServiceManager.editRecommendation(this, recommendation)
        }
    }

    override fun deleteRecommendation() {
        if (model.recommendation != null) {
            ServiceManager.deleteRecommendation(this, model.recommendation?.id.toString())
        }
    }

    override fun onPostRecommendationError() {
        view?.stopProgressDialog()
    }

    override fun onPostRecommendationSuccess(recommendation: RecommendationDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

    override fun onPutRecommendationError() {
        view?.stopProgressDialog()
    }

    override fun onPutRecommendationSuccess(recommendation: RecommendationDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

    override fun onDeleteRecommendationError() {
        view?.stopProgressDialog()
    }

    override fun onDeleteRecommendationSuccess() {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

    override fun getLocalTimeFromString(text: String?): LocalTime? {
        return if (text == null || text.isEmpty() || text == ResUtil.getString(R.string.time_placeholder)) {
            null
        } else {
            LocalTime.parse(text)
        }
    }

}

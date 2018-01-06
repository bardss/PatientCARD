package com.patientcard.views.recommendations

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.GetRecommendationsReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class RecommendationsPresenterImpl : BaseAbstractPresenter<RecommendationsView>(), RecommendationsPresenter, GetRecommendationsReciever {

    private val model: RecommendationsModel by lazy { RecommendationsModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        model.patientId = patientId
        model.patientName = patientName
    }

    override fun onViewAttached(view: RecommendationsView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
        view?.setupButtoms(model.patientId, model.patientName)
    }

    override fun getRecommendations() {
        val patientId: String? = model.patientId
        if (patientId != null) {
            view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
            ServiceManager.getRecommendations(this, patientId)
        }
    }

    override fun openEditRecommendation(recommendation: RecommendationDTO?) {
        view?.openEditRecommendation(model.patientId, model.patientName, recommendation)
    }

    override fun onGetRecommendationsError() {
        view?.stopProgressDialog()
    }

    override fun onGetRecommendationsSuccess(recommendations: List<RecommendationDTO>) {
        view?.setRecommendationsList(recommendations)
        view?.stopProgressDialog()
    }

}

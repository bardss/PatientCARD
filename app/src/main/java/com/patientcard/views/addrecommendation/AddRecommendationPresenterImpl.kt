package com.patientcard.views.addrecommendation

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.PostRecommendationReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter
import org.threeten.bp.LocalTime

class AddRecommendationPresenterImpl : BaseAbstractPresenter<AddRecommendationView>(), AddRecommendationPresenter, PostRecommendationReciever{

    private val presentationModel: AddRecommendationModel by lazy { AddRecommendationModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        presentationModel.patientId = patientId
    }

    override fun saveRecommendation(recommendation: RecommendationDTO) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        recommendation.patientId = presentationModel.patientId?.toLong()
        ServiceManager.addRecommendation(this, recommendation)
    }

    override fun onPostRecommendationError() {
        view?.stopProgressDialog()
    }

    override fun onPostRecommendationSuccess(recommendation: RecommendationDTO) {
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

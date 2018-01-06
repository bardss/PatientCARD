package com.patientcard.views.addrecommendation

import android.content.Intent
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.base.BasePresenter
import org.threeten.bp.LocalTime

interface AddRecommendationPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun saveRecommendation(recommendation: RecommendationDTO)
    fun getLocalTimeFromString(text: String?): LocalTime?
    fun deleteRecommendation()
}


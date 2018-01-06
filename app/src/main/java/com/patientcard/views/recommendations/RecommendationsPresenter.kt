package com.patientcard.views.recommendations

import android.content.Intent
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.base.BasePresenter

interface RecommendationsPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun getRecommendations()
    fun openEditRecommendation(recommendation: RecommendationDTO?)

}


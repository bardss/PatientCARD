package com.patientcard.views.recommendations

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.base.BaseView

interface RecommendationsView : BaseView {
    fun setRecommendationsList(recommendations: List<RecommendationDTO>)
    fun setupButtoms(patientId: String?)
}

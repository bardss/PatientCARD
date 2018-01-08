package com.patientcard.views.recommendations

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.base.BaseView

interface RecommendationsView : BaseView {
    fun setRecommendationsList(recommendations: List<RecommendationDTO>)
    fun setupButtoms(patientId: String?, patientName: String?)
    fun setPatientName(patientName: String?)
    fun clickEditRecommendation(recommendation: RecommendationDTO?)
    fun openEditRecommendation(patientId: String?, patientName: String?, recommendation: RecommendationDTO?)
    fun setupEmptyView()
}
package com.patientcard.views.addrecommendation

import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.base.BaseView

interface AddRecommendationView : BaseView {
    fun navigateBack()
    fun setPatientName(patientName: String?)
    fun fillFields(recommendation: RecommendationDTO?)
    fun setupDeleteIcon(recommendation: RecommendationDTO?)
    fun setTitle(title: String?)
    fun setLabel()
}

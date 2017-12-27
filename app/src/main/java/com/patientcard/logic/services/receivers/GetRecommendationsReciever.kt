package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.RecommendationDTO

interface GetRecommendationsReciever {

    fun onGetRecommendationsSuccess(recommendations: List<RecommendationDTO>)

    fun onGetRecommendationsError()
}
package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.RecommendationDTO

interface PostRecommendationReciever {

    fun onPostRecommendationSuccess(recommendation: RecommendationDTO)

    fun onPostRecommendationError()
}
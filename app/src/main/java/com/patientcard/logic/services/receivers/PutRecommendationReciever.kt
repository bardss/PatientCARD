package com.patientcard.logic.services.receivers

import com.patientcard.logic.model.transportobjects.RecommendationDTO

interface PutRecommendationReciever {

    fun onPutRecommendationSuccess(recommendation: RecommendationDTO)

    fun onPutRecommendationError()
}
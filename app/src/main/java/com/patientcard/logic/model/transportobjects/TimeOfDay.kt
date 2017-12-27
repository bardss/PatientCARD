package com.patientcard.logic.model.transportobjects

import com.patientcard.R
import com.patientcard.logic.utils.ResUtil

enum class TimeOfDay(val stringValue: String?){
    MORNING(ResUtil.getString(R.string.morning)),
    NOON(ResUtil.getString(R.string.noon)),
    EVENING(ResUtil.getString(R.string.evening)),
    NIGHT(ResUtil.getString(R.string.night))
}
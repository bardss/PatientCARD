package com.patientcard.views.shortfever

import android.content.Intent
import com.patientcard.views.base.BasePresenter

interface ShortFeverPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun getFeverCard()

}


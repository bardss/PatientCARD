package com.patientcard.views.addfevercard

import android.content.Intent
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.base.BasePresenter

interface AddFeverCardPresenter : BasePresenter {

    override fun initExtras(intent: Intent)
    fun addFeverCard(feverCard: FeverCardDTO)

}
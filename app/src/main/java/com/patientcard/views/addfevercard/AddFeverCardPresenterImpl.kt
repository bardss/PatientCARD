package com.patientcard.views.addfevercard

import android.content.Intent
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.views.base.BaseAbstractPresenter

class AddFeverCardPresenterImpl : BaseAbstractPresenter<AddFeverCardView>(), AddFeverCardPresenter {

    private val presentationModel: AddFeverCardModel by lazy { AddFeverCardModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        presentationModel.patientId = patientId
        presentationModel.patientName = patientName
    }

    override fun onViewAttached(view: AddFeverCardView?) {
        super.onViewAttached(view)
        view?.setPatientName(presentationModel.patientName)
        view?.setupButton()
    }

}
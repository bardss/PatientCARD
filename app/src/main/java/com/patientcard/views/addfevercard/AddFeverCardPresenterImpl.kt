package com.patientcard.views.addfevercard

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.PostFeverCardReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class AddFeverCardPresenterImpl : BaseAbstractPresenter<AddFeverCardView>(), AddFeverCardPresenter, PostFeverCardReciever {

    private val model: AddFeverCardModel by lazy { AddFeverCardModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        model.patientId = patientId
        model.patientName = patientName
    }

    override fun onViewAttached(view: AddFeverCardView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
        view?.setupButton()
    }

    override fun addFeverCard(feverCard: FeverCardDTO) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        feverCard.patientId = model.patientId?.toLong()
        ServiceManager.addFeverCard(this, feverCard)
    }

    override fun onPostFeverCardError() {
        view?.stopProgressDialog()
    }

    override fun onPostFeverCardSuccess(feverCard: FeverCardDTO) {
        view?.stopProgressDialog()
        view?.navigateBack()
    }

}
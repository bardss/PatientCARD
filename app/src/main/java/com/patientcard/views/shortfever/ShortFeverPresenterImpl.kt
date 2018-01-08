package com.patientcard.views.shortfever

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.GetFeverCardReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class ShortFeverPresenterImpl : BaseAbstractPresenter<ShortFeverView>(), ShortFeverPresenter, GetFeverCardReciever {

    private val model: ShortFeverModel by lazy { ShortFeverModel() }

    override fun initExtras(intent: Intent) {
        val patientId: String? = intent.getSerializableExtra(IntentKeys.PATIENT_ID) as String?
        val patientName: String? = intent.getSerializableExtra(IntentKeys.PATIENT_NAME) as String?
        model.patientId = patientId
        model.patientName = patientName
    }

    override fun onViewAttached(view: ShortFeverView?) {
        super.onViewAttached(view)
        view?.setPatientName(model.patientName)
    }

    override fun getFeverCard() {
        val patientId: String? = model.patientId
        if (patientId != null) {
            view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
            ServiceManager.getFeverCard(this, patientId)
        }
    }

    override fun onGetFeverCardError() {
        view?.stopProgressDialog()
    }

    override fun onGetFeverCardSuccess(feverCard: List<FeverCardDTO>) {
        view?.stopProgressDialog()
        view?.setupButtons(feverCard as ArrayList<FeverCardDTO>, model.patientName, model.patientId)
        if (!feverCard.isEmpty()) {
            view?.setFeverCard(feverCard)
        } else {
            view?.setupEmptyView()
        }
    }

}

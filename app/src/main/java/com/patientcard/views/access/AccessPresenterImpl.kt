package com.patientcard.views.access

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.database.Database
import com.patientcard.logic.model.transportobjects.TokenDTO
import com.patientcard.logic.services.ServiceManager
import com.patientcard.logic.services.receivers.LoginDoctorReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseAbstractPresenter

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter, LoginDoctorReciever {

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun loginDoctor(login: String, password: String) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.loginDoctor(this, login, password)
    }

    override fun onLoginDoctorSuccess(tokenDTO: TokenDTO) {
        saveToken(tokenDTO)
        view?.checkPermission()
        view?.stopProgressDialog()
    }

    override fun onLoginDoctorError() {
        view?.stopProgressDialog()
    }
    
    private fun saveToken(tokenDTO: TokenDTO) {
        Database.putToken(tokenDTO)
    }

}

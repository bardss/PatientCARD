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

    private val model: AccessModel by lazy { AccessModel() }

    override fun initExtras(intent: Intent) {
        // no extras
    }

    override fun onViewAttached(view: AccessView?) {
        super.onViewAttached(view)
        fillLogin()
    }

    private fun fillLogin() {
        val login = Database.getLogin()
        view?.fillLogin(login)
    }

    override fun loginDoctor(login: String, password: String) {
        view?.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        model.login = login
        ServiceManager.loginDoctor(this, login, password)
    }

    override fun onLoginDoctorSuccess(tokenDTO: TokenDTO) {
        saveToken(tokenDTO)
        Database.putLogin(model.login)
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

package com.patientcard.views.access

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.qrrcode.QRCodeActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.yanzhenjie.permission.PermissionListener
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_access.*

@ActivityView(layout = R.layout.activity_access, presenter = AccessPresenterImpl::class)
class AccessActivity : BaseActivity(), AccessView {

    @Presenter
    lateinit var presenter: AccessPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupLoginButton()
    }

    override fun fillLogin(login: String) {
        loginEditText.setText(login)
    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.loginDoctor(login, password)
        }
    }

    private fun openQRReaderActivity(cameraPermission: Boolean) {
        startActivity(Intent(this, QRCodeActivity::class.java)
                .putExtra(IntentKeys.CAMERA_PERMISSION, cameraPermission))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun checkPermission(){
        AndPermission
                .with(this)
                .requestCode(0)
                .permission(Permission.CAMERA)
                .callback(object : PermissionListener {
                    override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                        openQRReaderActivity(true)
                    }

                    override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                        openQRReaderActivity(false)
                    }
                })
                .start()
    }

    override fun setInputErrors() {
        loginEditText.error = ResUtil.getString(R.string.wrong_login_or_password)
        passwordEditText.error = ResUtil.getString(R.string.wrong_login_or_password)
    }
    
}

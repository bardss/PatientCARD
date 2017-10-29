package com.rsqtechnologies.rsqphysio.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.base.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var progress: ProgressDialog? = null
    private var dialogCloseButton: ImageView? = null
    private var isCloseableOnBackButton = false

    override val activityContext: Context
        get() = this

    override fun onStart() {
        super.onStart()
        initExtras(providePresenter())
    }

    override fun startProgressDialog(message: String) {
        if ((progress == null || !progress!!.isShowing) && !isFinishing) {
            progress = ProgressDialog.show(this@BaseActivity, getString(R.string.progress_loading_text),
                    message, true)
            Handler().postDelayed({
                if (progress != null && !isFinishing && progress!!.isShowing) {
                    progress!!.cancel()
                }
            }, 10000)
        }
    }

    override fun stopProgressDialog() {
        if (progress != null && !isFinishing && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }

    internal fun initExtras(presenter: BasePresenter?) {
        if (presenter != null) {
            presenter!!.initExtras(intent)
        }
    }

    abstract fun providePresenter(): BasePresenter?

    fun openActivity(classTo: Class<*>) {
        startActivity(Intent(this, classTo))
    }

    fun performOnBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (!isCloseableOnBackButton && dialogCloseButton != null && dialogCloseButton!!.visibility == View.VISIBLE) {
            dialogCloseButton!!.performClick()
        } else {
            super.onBackPressed()
        }
    }

    fun setDialogCloseable(dialogCloseButton: ImageView) {
        this.dialogCloseButton = dialogCloseButton
    }

    fun setCloseBackButton(isCloseableOnBackButton: Boolean) {
        this.isCloseableOnBackButton = isCloseableOnBackButton
    }

}

package com.patientcard.views.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.patientcard.R

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

    override fun startProgressDialog(message: String?) {
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
            progress?.dismiss()
        }
    }

    internal fun initExtras(presenter: BasePresenter?) {
        if (presenter != null) {
            presenter!!.initExtras(intent)
        }
    }

    abstract fun providePresenter(): BasePresenter?

    override fun performOnBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        if (!isCloseableOnBackButton && dialogCloseButton != null && dialogCloseButton!!.visibility == View.VISIBLE) {
            dialogCloseButton!!.performClick()
        } else {
            super.onBackPressed()
        }
    }

}
